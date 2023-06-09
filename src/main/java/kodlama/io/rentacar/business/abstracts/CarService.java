package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.enums.State;

import java.util.List;

public interface CarService {

    List<GetAllCarsResponse> getAll(boolean includeMaintenance);
    // false gelirse bakımda olan araçları getirmeyecek
    // true gelirse(default olarak true) hepsini getiricek

    GetCarResponse getById(int id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(int id, UpdateCarRequest request);

    void delete(int id);

    void changeState(int carId, State state);
    // aracın durumunu değiştiricek

}
