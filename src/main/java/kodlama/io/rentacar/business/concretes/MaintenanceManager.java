package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.concretes.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = repository.findMaintenanceByCarIdAndIsCompletedFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkCarAvailabilityForMaintenance(request.getCarId());
        checkIfCarUnderMaintenance(request.getCarId());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        repository.save(maintenance);

        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);
        repository.deleteById(id);
    }

    private void checkIfMaintenanceExists(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Böyle bir bakım bilgisine ulaşılamadı!");
        }
    }

    private void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("Araç şu anda bakımda!");
        }
    }

    private void checkIfCarIsNotUnderMaintenance(int carId){
        if(!repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");
        }
    }

    private void checkCarAvailabilityForMaintenance(int carId) {
        if (carService.getById(carId).getState().equals(State.RENTED)) {
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }

    private void makeCarAvailableIfIsCompletedFalse(int id){
        int carId = repository.findById(id).get().getCar().getId();
        if(repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            carService.changeState(carId, State.AVAILABLE);
        }
    }















  /*


    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow(); //null
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        // aracın bakımdan dönmesi

        checkIfCarIsNotUnderMaintenance(carId);
        // araç bakımda değil
        // aracın bakımda olmadığı durumu kontrol ediyor

        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        //repository deki sorguyu kullandık

        maintenance.setCompleted(true);
        // sorgudan getiricek carId den gelene göre aracın bakım durumunu true yapıcak

        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance); // Update
        carService.changeState(carId, State.AVAILABLE);
        // araç bakımdan döndüğü için artık available durumda

        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkIfCarUnderMaintenance(request); // araç zaten bakımda mı?
        checkCarAvailabilityForMaintenance(request); //kirada olan araç bakıma gidemez
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExists(id); // zaten bakımda olmayan araç update edilemez
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        //   int carId = repository.findById(id).get().getCar().getId();
        // checkIfCarIsNotUnderMaintenance(carId); // bakımda mı değil mi kontrols
        makeCarAvailableIfIsCompletedFalse(id);
        // carService.changeState(carId, State.AVAILABLE);
        repository.deleteById(id);
    }


    private void checkIfMaintenanceExists(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Böyle bir bakım bilgisine ulaşılamadı!");
        }

    }

    private void checkIfCarIsNotUnderMaintenance(int carId) {
        // aracın bakımda olmadığı durumu kontrol ediyor

        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");

        }
    }

    private void checkIfCarUnderMaintenance(CreateMaintenanceRequest request) {
        // aracın bakımda olduğu durumu kontrol ediyor

        if (repository.existsByCarIdAndIsCompletedIsFalse(request.getCarId())) {
            throw new RuntimeException("Araç şu anda bakımda!");
        }
    }

    private void checkCarAvailabilityForMaintenance(CreateMaintenanceRequest request) {
        // kirada olan araç bakıma gidemez
        if (carService.getById(request.getCarId()).getState().equals(State.RENTED)) {
            // carId den state bak; eğer state rented e eşitse hata mesajı gönder
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }

    private void makeCarAvailableIfIsCompletedFalse(int id){
        int carId = repository.findById(id).get().getCar().getId();
        if(repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            carService.changeState(carId, State.AVAILABLE);
        }
    }
    */
}
