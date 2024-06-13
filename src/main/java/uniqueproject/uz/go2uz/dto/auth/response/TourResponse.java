package uniqueproject.uz.go2uz.dto.auth.response;

import lombok.*;
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
public class TourResponse {
    private UUID id;
    private String title;
    private String description; //  joy haqida description
    private List<String> pictures; // rasmlar pathi
    private Double cost; // narxi
    private String location;
    private List<String> services;
    private TourCategory category;
    private TourStatus status;
    private String agencyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer availableSeats;
}
