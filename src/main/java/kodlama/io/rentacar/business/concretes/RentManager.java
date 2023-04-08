package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.RentService;
import kodlama.io.rentacar.business.dto.requests.create.CreateRentRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateRentRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateRentResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetRentResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllRentsResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateRentResponse;
import kodlama.io.rentacar.entities.concretes.Rent;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class RentManager implements RentService {
    private final RentRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    @Override
    public List<GetAllRentsResponse> getAll() {
        List<Rent> rents = repository.findAll();
        List<GetAllRentsResponse> response = rents
                .stream()
                .map(rent -> mapper.map(rent, GetAllRentsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetRentResponse getById(int id) {
        checkIfRentExists(id);
        Rent rent = repository.findById(id).orElseThrow(); //null
        GetRentResponse response = mapper.map(rent, GetRentResponse.class);

        return response;
    }

    @Override
    public GetRentResponse returnCarFromRent(int carId) {
        checkIfCarIsNotUnderRent(carId);
        Rent rent = repository.findByCarIdIsCompletedIsFalse(carId);
        rent.setCompleted(true);
        rent.setEndDate(LocalDateTime.now());
        double dailyPrice = carService.getDailyPrice(carId);
        rent.setTotalPrice(dailyPrice* rent.getRentedForDays());
        repository.save(rent);
        carService.changeState(carId, State.AVAILABLE);
        GetRentResponse response = mapper.map(rent,GetRentResponse.class);

        return response;
    }

    @Override
    public CreateRentResponse add(CreateRentRequest request) {
        checkCarAvailabilityForRent(request.getCarId());
        checkIfCarUnderRent(request.getCarId());
        Rent rent = mapper.map(request, Rent.class);
        rent.setId(0);
        rent.setStartingDate(LocalDateTime.now());
        rent.setEndDate(null);
        repository.save(rent);
        carService.changeState(request.getCarId(), State.RENTED);
        CreateRentResponse response = mapper.map(rent, CreateRentResponse.class);

        return response;
    }

    @Override
    public UpdateRentResponse update(int id, UpdateRentRequest request) {
        checkIfRentExists(id);
        Rent rent = mapper.map(request, Rent.class);
        rent.setId(id);
        repository.save(rent);
        UpdateRentResponse response = mapper.map(rent, UpdateRentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfRentExists(id);
        makeCarAvailableIfIsCompletedFalse(id);
        repository.deleteById(id);

    }

    private void checkIfRentExists(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Böyle bir kiralama bilgisine ulaşılamadı!");
        }
    }
    private void makeCarAvailableIfIsCompletedFalse(int id) { //int carId
        int carId = repository.findById(id).get().getCar().getId();
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            carService.changeState(carId, State.AVAILABLE);
        }
    }

    private void checkIfCarIsNotUnderRent(int carId) {
        // aracın kiralık olmadığı durumu kontrol ediyor

        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("kiralık böyle bir araç bulunamadı!");

        }
    }

    private void checkIfCarUnderRent(int carId) {
        // aracın kiralık olduğu durumu kontrol ediyor

        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("Araç şuanda kiralık!");
        }
    }

    private void checkCarAvailabilityForRent(int carId) {
        // bakımda olan araç  kiralanamaz!
        if (carService.getById(carId).getState().equals(State.MAINTENANCE)) {
            throw new RuntimeException("Araç bakımda olduğu için kiralanamaz!");
        }
    }










}
/*

        3. Kirada veya bakımda olan bir araç kiralanamamalı.
        4. totalPrice alanı sadece okunabilmelidir.
        5. Kiradan dönen aracın durumu (rental delete işlemi ile) güncellenmelidir.
        6. Kiralama nesnesinde olması gereken özellikler: id, carId, dailyPrice, rentedForDays, totalPrice, startDate.
*/