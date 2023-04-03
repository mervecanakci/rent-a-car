package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.CarMaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car_maintenances")
@AllArgsConstructor
public class CarMaintenanceController {
    private final CarMaintenanceService service;

    @GetMapping
    public List<GetAllCarMaintenanceResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCarMaintenanceResponse getById(@PathVariable int id){
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateCarMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateCarMaintenanceRequest carMaintenanceRequest){
        return service.update(id, carMaintenanceRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarMaintenanceResponse add(@RequestBody CreateCarMaintenanceRequest carMaintenanceRequest){
        return service.add(carMaintenanceRequest);
    }

}
