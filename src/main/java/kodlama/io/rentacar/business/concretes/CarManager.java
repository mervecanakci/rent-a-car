package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.concretes.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;


    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        List<Car> cars = filterCarsByMaintenanceState(includeMaintenance);
        List<GetAllCarsResponse> response = cars
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExists(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        car.setState(State.AVAILABLE);
        repository.save(car);
        CreateCarResponse response = mapper.map(car, CreateCarResponse.class);

        return response;
    }
    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExists(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.map(car, UpdateCarResponse.class);

        return response;
    }
    @Override
    public void delete(int id) {
        checkIfCarExists(id);
        repository.deleteById(id);
    }

    @Override
    public void changeState(int carId, State state) {
        Car car = repository.findById(carId).orElseThrow();
        car.setState(state);
        repository.save(car);
    }


  /*  @Override
    public double getDailyPrice(int carId) {
   return repository.findById(carId).get().getDailyPrice();

    } */

    private void checkIfCarExists(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Böyle bir araç bulunamadı!");
        } //? bu metodu neden update e delete falan koyuyuyoruz ?//
    }

    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        // includeMaintenance ; bakımda olanları dahil edeyim mi diyor
        //true ise hepsini getir
        if (includeMaintenance) {
            return repository.findAll();
        }
        // false ise maintenance ı çıkarıp diğerlerini getiricek
        return repository.findAllByStateIsNot(State.MAINTENANCE);
    }

      /*

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        List<GetAllCarsResponse> response = cars
                .stream()//map diye bir fonksiyon kullanmamızı sağlıyor
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                //her bir brand i bir tane responsa çeviricek sanırım
                .toList();

        return response;
    }
    */
}
