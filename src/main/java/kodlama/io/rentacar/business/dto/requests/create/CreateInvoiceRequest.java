package kodlama.io.rentacar.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    private String cardHolder;
    private String modelName;
    private String brandName;
    private String plate;
    private int modelYear;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDateTime rentedAt;
}
