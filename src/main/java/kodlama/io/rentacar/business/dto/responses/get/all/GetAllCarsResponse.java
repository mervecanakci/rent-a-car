package kodlama.io.rentacar.business.dto.responses.get.all;

import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
    private int id;
    private int modelId;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
    private String modelName;
    private String modelBrandName; // brandName i modeli vermeden gösteremiyor
    //mapper ın çalışma mantığı bu modelden brandName i alıyor

}
