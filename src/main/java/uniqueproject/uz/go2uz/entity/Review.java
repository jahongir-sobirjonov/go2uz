package uniqueproject.uz.go2uz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review extends BaseEntity {
    @ManyToOne
    private UserEntity author;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_review_id")
//    private Review parentReview;

//    @OneToMany(mappedBy = "parentReview", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Review> replies;
}
