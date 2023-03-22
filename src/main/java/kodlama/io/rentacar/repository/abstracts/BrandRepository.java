package kodlama.io.rentacar.repository.abstracts;

import kodlama.io.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandRepository {
    //sen brand ların listesisin dedik burada: List<Brand>getAll();
    //brandların listesi gelicek getAll ı çağırdığımızda

    List<Brand>getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    void delete(int id);

}


//koleksiyonlarla listelerin farkı