package uniqueproject.uz.go2uz.dto.auth.request;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.entity.enums.ServiceType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgencyRequest {
    private String name;
    private List<Tour> tours;
    private List<ServiceType> serviceTypes;
    private Integer countOfOrders;
    private Integer rating;
}
