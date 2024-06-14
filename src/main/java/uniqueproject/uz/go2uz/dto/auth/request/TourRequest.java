package uniqueproject.uz.go2uz.dto.auth.request;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.entity.enums.TourCategory;
import uniqueproject.uz.go2uz.entity.enums.TourStatus;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TourRequest {
    private UUID agencyId;
    private String title;
    private String description;
    private List<String> pictures;
    private Double cost;
    private String location;
    private TourCategory category;
    private TourStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer availableSeats;
    private List<String> services;
    private String cancellationPolicy;

}
