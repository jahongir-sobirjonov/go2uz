package uniqueproject.uz.go2uz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "agencies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Agency extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY) //
    private List<Tour> tours;

    private Integer countOfOrders;
    private Integer rating;
}

