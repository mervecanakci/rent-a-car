package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
 //custom queries
    Brand getByBrandId(int brandId);
    boolean existsByBrandName(String brandName);
    Brand getByBrandName(String brandName);




 /*
    //sen brand ların listesisin dedik burada: List<Brand>getAll();
    //brandların listesi gelicek getAll ı çağırdığımızda

    List<Brand>getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    void delete(int id);

  */

}


//koleksiyonlarla listelerin farkı