package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.all.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.concretes.Model;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll(); // daha okunaklı olması için yazman daha iyi olur
        List<GetAllModelsResponse> response = models
                //mapper la döndürmek istediğimiz tip : GetAllModelsResponse
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                // sonra her bir model için (model ->) mapper ı çağır
                //map de modelin her birini GetAllModelsResponse a çevir
                .toList();  // bunu listeye çevir

        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExists(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class); //requestten geleni mapledik
        model.setId(0); //yeni bir tane oluştur create anlamında date base i 1 den başlat
        //dto kaynaklı bu gerekli diğer id ile karıştırabilir
        repository.save(model);
        CreateModelResponse response = mapper.map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        //*  if(!repository.existsById(id)){ //id ye ait bir şey varsa true dönüyor
        //true dönerse de içerisi çalışacak
        /*** ama biz false olursa içi çalışsın istiyoruz bu nedenle !repository var */
        //   throw new RuntimeException("BÖYLE BİR MODEL BULUNAMADI");
        // }
        // ! ctrl+alt+m  = "checkIfModelExistsById metodunu oluşturdu  !//

        checkIfModelExists(id);
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        repository.save(model);
        UpdateModelResponse response = mapper.map(model, UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfModelExists(id);
        repository.deleteById(id);
    }

    private void checkIfModelExists(int id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Böyle bir model bulunamadı!");
        }
    }
}
