package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarMaintenanceService;
import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarMaintenanceResponse;
import kodlama.io.rentacar.entities.concretes.Car;
import kodlama.io.rentacar.entities.concretes.CarMaintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarMaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarMaintenanceManager implements CarMaintenanceService {
    private final CarMaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;


    @Override
    public List<GetAllCarMaintenanceResponse> getAll() {
        List<CarMaintenance> carMaintenances = repository.findAll();
        List<GetAllCarMaintenanceResponse> response = carMaintenances.stream()
                .map(carMaintenance -> mapper.map(carMaintenance, GetAllCarMaintenanceResponse.class)).toList();
        return response;
    }

    @Override
    public GetCarMaintenanceResponse getById(int id) {
        CarMaintenance carMaintenance = repository.findById(id).orElseThrow();
        GetCarMaintenanceResponse response = mapper.map(carMaintenance, GetCarMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateCarMaintenanceResponse add(CreateCarMaintenanceRequest request) {
        CarMaintenance carMaintenanceSave = mapper.map(request, CarMaintenance.class);
        carMaintenanceSave.setId(0);
        GetCarResponse car = carService.getById(request.getCarId());
        Car carSave = mapper.map(car, Car.class);
        checkIfCarCanBeSentToMaintenance(carSave);
        repository.save(carMaintenanceSave);
        carSave.setState(State.MAINTENANCE);
        UpdateCarRequest carUpdate = mapper.map(carSave, UpdateCarRequest.class);
        carService.update(carSave.getId(), carUpdate);
        CreateCarMaintenanceResponse response = mapper.map(carMaintenanceSave, CreateCarMaintenanceResponse.class);
        return response;
    }

    @Override
    public UpdateCarMaintenanceResponse update(int id, UpdateCarMaintenanceRequest request) {
        CarMaintenance updateCarMaintenance = mapper.map(request, CarMaintenance.class);
        updateCarMaintenance.setId(id);
        GetCarResponse car = carService.getById(request.getCarId());
        Car carSave = mapper.map(car, Car.class);
        checkIfCarCanBeSentToMaintenance(carSave);
        repository.save(updateCarMaintenance);
        carSave.setState(State.MAINTENANCE);
        UpdateCarRequest carUpdate = mapper.map(carSave, UpdateCarRequest.class);

        carService.update(carSave.getId(), carUpdate);
        UpdateCarMaintenanceResponse response = mapper.map(updateCarMaintenance, UpdateCarMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private void checkIfCarCanBeSentToMaintenance(Car car) {
        checkIfCarInMaintenance(car);
        checkIfCarRented(car);
    }

    private void checkIfCarRented(Car car) {
        if(car.getState() == State.RENTED){
            throw new RuntimeException("Car is rented");
        }
    }

    private void checkIfCarInMaintenance(Car car) {
        if(car.getState() == State.MAINTENANCE){
            throw new RuntimeException("Car is already in maintenance!");
        }

    }
}
