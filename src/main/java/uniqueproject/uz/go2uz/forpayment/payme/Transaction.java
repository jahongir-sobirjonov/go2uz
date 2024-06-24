package uniqueproject.uz.go2uz.forpayment.payme;


import jakarta.persistence.*;
import lombok.*;
import uniqueproject.uz.go2uz.entity.BaseEntity;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.UserEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Double amount;
    private String url;


}
