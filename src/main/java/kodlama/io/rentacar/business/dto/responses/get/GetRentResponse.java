package kodlama.io.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentResponse {
    private int id;
    private int carId;
    private LocalDateTime startingDate;
    private LocalDateTime endDate;
    private double totalPrice;
    private int rentedForDays;
    private boolean isCompleted;

    private double startingKilometer;
    private double returnKilometer;
}
