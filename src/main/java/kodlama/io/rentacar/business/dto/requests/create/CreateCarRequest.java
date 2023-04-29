package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kodlama.io.rentacar.cammon.constants.Messages;
import kodlama.io.rentacar.cammon.constants.Regex;
import kodlama.io.rentacar.cammon.utils.annotations.NotFutureYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    @Min(0)
    private int modelId;
    @Min(1996)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate, message = Messages.Car.PlateNotValid)
    private String plate;
    @Min(1)
    @Max(100000)
    private double dailyPrice;
}
