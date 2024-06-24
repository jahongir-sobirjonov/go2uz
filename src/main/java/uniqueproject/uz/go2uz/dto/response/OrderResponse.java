package uniqueproject.uz.go2uz.dto.response;
import lombok.*;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private UUID id;
    private UUID userId;
    private UUID tourId;
    private String phoneNumber;
    private OrderStatus status;
    private LocalDate orderDate;
    private UUID orderId;
    private Integer numberOfSeats;
    private Double totalCost;
//    private String url;


}
