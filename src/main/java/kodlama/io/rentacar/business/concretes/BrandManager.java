package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager implements BrandService {
    @Autowired
    private final BrandRepository repository;


    public BrandManager(BrandRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Brand> getAll() {
        if(repository.getAll().size() == 0 ) throw new RuntimeException("Kayıtlı marka yok!!");

        return repository.getAll();


    }
    @Override
    public Brand getById(int id) {
        return repository.getById(id);
    }

    @Override
    public Brand add(Brand brand) {
        return repository.add(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        return repository.update(id, brand);
    }

    @Override
    public void delete(int id) {
repository.delete(id);
    }
    //! Business Rules




}
