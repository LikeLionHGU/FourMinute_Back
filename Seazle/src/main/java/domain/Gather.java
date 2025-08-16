package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gather extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String sport;
    private String time;
    private Long capacity;
    private String description;
    private String originalPrice;
    private String currentPrice;
    private String deadline;
    private String maker;
    private Long total;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Location location;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user; //방장

    @OneToMany(mappedBy="gather", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();


}
