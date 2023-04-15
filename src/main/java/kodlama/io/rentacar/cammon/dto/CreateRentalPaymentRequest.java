package kodlama.io.rentacar.cammon.dto;

import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalPaymentRequest extends PaymentRequest {
    private String cardNumber;
    private String cardHolder;
    private int cardExpirationYear;
    private int cardExpirationMonth;
    private String cardCvv;
    private double price;
}//
