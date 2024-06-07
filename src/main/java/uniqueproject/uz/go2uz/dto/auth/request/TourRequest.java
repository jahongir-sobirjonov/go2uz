package uniqueproject.uz.go2uz.dto.auth.request;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uniqueproject.uz.go2uz.dto.auth.response.TourResponse;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.entity.enums.TourCategory;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TourRequest {
    private String title;
    private String description; //  joy haqida description
    private List<String> pictures; // rasmlar pathi
    private Double cost; // narxi
    private LocalDate date; // sanasi
    private String location;
    private TourCategory category;

}
