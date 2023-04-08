package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.Car;
import kodlama.io.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByStateIsNot(State state);
    // state i hangi durumda istemiyorsan o durumu dışlayacak ve diğer durumları getirecek
    //carManager.getAll da yapıyoruz bu filtreleme işlemini

}
