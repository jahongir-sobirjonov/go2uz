package uniqueproject.uz.go2uz.entity;
import jakarta.persistence.*;
import lombok.*;
import uniqueproject.uz.go2uz.entity.enums.TourCategory;
import uniqueproject.uz.go2uz.entity.enums.TourStatus;

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
    private LocalDate startDate; // stratDateni o'zgartirish imkoni beriladi Agencyga
    private LocalDate endDate;
    private String location;

    @Enumerated(EnumType.STRING)
    private TourCategory category;

    @Enumerated(EnumType.STRING)
    private TourStatus status;

    private List<String> services;

//    private Integer totalSeats;
    private Integer availableSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    private Agency agency;

}
