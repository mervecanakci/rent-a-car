package kodlama.io.rentacar.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentRequest {
    private int carId;
  //  private double rentalPriceOfTheCar;
     private double startingKilometer;
    private double returnKilometer;
    private double dailyPrice;
    private int rentedForDays;


}
