package uniqueproject.uz.go2uz.forpayment.payme;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
class PaymentRequest {
    private String paymentType;
    private Double amount;
    private String returnUrl;
}
