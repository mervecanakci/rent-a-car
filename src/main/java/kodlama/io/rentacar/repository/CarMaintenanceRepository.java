package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, Integer> {

}
