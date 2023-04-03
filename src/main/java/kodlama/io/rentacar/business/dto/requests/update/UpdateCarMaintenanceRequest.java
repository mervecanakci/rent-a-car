package kodlama.io.rentacar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
    private String description;
    private LocalDate sendDate;
    private LocalDate returnDate;
    private int carId;
}