package uniqueproject.uz.go2uz.dto.request;

import lombok.*;
import uniqueproject.uz.go2uz.entity.enums.TourCategory;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilterToursRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Double minPrice;
    private Double maxPrice;
    private String location;
    private TourCategory category;
}
