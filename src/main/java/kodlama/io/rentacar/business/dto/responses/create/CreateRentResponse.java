package kodlama.io.rentacar.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentResponse {
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
