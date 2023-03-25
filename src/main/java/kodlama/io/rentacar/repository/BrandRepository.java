package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    //custom queries
  /*  Brand getByBrandId(int brandId);

    boolean existsByBrandName(String brandName);

    Brand getByBrandName(String brandName);
*/
}
