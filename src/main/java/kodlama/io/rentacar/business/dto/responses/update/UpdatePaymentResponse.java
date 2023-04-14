package kodlama.io.rentacar.business.dto.responses.update;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentResponse {
    private int id;
    private String cardNumber;
    private String cardHolder;
    private int cardExpirationYear;
    private int cardExpirationMonth;
    private String cardCvv;
    private double balance;
}
