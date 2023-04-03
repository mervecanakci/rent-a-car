package kodlama.io.rentacar.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceResponse {
    private int id;
    private String description;
    private LocalDate sendDate;
    private LocalDate returnDate;
    private int carId;

}
