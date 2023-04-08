package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    //Rent findByCarIdIsCompletedIsFalse(int carId);
   // boolean existsByCarIdAndIsCompletedIsFalse(int carId);



}
