package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Integer> {
    Rent findByCarIdIsCompletedIsFalse(int carId);
    boolean existsByCarIdAndIsCompletedIsFalse(int carId);



}
