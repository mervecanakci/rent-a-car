package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private State state;
}
