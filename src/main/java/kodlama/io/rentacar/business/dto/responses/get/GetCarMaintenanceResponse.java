package kodlama.io.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarMaintenanceResponse {
    private int id;
    private String description;
    private LocalDate sendDate;
    private LocalDate returnDate;
    private int carId;

}
