package uniqueproject.uz.go2uz.dto.auth.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private UUID userId;
    private UUID tourId;
    private String phoneNumber;
    private Integer NumberOfSeats;
}
