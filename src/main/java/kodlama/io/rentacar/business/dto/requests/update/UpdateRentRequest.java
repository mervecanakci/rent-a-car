package kodlama.io.rentacar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentRequest {
    private int carId;
    private LocalDateTime startingDate;
    private LocalDateTime endDate;
    private double totalPrice;
    private int rentedForDays;
    private boolean isCompleted;
    private double startingKilometer;
    private double returnKilometer;
}
