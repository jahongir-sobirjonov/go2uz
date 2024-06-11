package uniqueproject.uz.go2uz.entity;
import jakarta.persistence.*;
import lombok.*;
import uniqueproject.uz.go2uz.entity.enums.TourCategory;
import java.time.LocalDate;
import java.util.List;
@Entity(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tour extends BaseEntity {
    private String title;
    private String description; //  joy haqida description
    private List<String> pictures; // rasmlar pathi
    private Double cost; // narxi
    private LocalDate date; // sanasi
    private String location;

    @Enumerated(EnumType.STRING)
    private TourCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    private Agency agency;

}
