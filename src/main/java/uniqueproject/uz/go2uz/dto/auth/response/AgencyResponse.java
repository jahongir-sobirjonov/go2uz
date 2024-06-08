package uniqueproject.uz.go2uz.dto.auth.response;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.entity.enums.ServiceType;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgencyResponse {
    private UUID id;
    private String name;
    private List<Tour> tours;
    private List<ServiceType> serviceTypes;
    private Integer countOfOrders;
    private Integer rating;
}


