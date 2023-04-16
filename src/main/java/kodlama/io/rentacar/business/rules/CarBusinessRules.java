package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.cammon.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfCarExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Car.NotExists);
        } //? bu metodu neden update e delete falan koyuyuyoruz ?//
    }
}
