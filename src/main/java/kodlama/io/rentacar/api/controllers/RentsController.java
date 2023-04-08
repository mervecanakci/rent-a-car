package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.RentService;
import kodlama.io.rentacar.business.dto.requests.create.CreateRentRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateRentRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateRentResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetRentResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllRentsResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateRentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/rents")
public class RentsController {
    private final RentService service;
    @GetMapping
    public List<GetAllRentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRentResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentResponse add(@RequestBody CreateRentRequest request) {
        return service.add(request);
    }

    @PutMapping("/return")
    public GetRentResponse returnCarFromRent(@RequestParam int carId) {
        return service.returnCarFromRent(carId);
    }

    @PutMapping("/{id}")
    public UpdateRentResponse update(@PathVariable int id, @RequestBody UpdateRentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

