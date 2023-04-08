package kodlama.io.rentacar.business.dto.responses.get.all;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
    private int id;
    private int carId;
    private String information;
    private boolean isCompleted;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
