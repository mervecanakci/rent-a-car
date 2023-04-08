package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.CreateRentRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateRentRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateRentResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetRentResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllRentsResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateRentResponse;

import java.util.List;

public interface RentService {
    List<GetAllRentsResponse> getAll();
    GetRentResponse getById(int id);
    GetRentResponse returnCarFromRent(int carId);
    CreateRentResponse add(CreateRentRequest request);
    UpdateRentResponse update(int id, UpdateRentRequest request);
    void delete(int id);
}
