package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
      // @Nonnull
     // Maintenance findById(int id);
      Maintenance findMaintenanceByCarIdAndIsCompletedFalse(int carId);
  //  Maintenance findByCarIdAndIsCompletedIsFalse(int carId);

    // gidecek tablolara bakıcak; carId ve isCompleted false ise
    // onu getirecek - yoksa zaten sorun yok -
    boolean existsByCarIdAndIsCompletedIsFalse(int carId);
    // false olarak aldık çünkü add kısmında araç bakımda mı buna bakması lazım öncelikle


    // veri tabanı tablosundan bulup getiricek verileri o nedenle repository de yazdık

}
