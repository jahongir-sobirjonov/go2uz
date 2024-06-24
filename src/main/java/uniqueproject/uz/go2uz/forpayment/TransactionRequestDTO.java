package uniqueproject.uz.go2uz.forpayment;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.UserEntity;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {
    private UUID orderId;
    private UUID userId;
    private Double amount;
    private String url;
}
