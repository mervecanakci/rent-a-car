package kodlama.io.rentacar.cammon.dto;

import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalPaymentRequest extends PaymentRequest {
    private double price;
}
