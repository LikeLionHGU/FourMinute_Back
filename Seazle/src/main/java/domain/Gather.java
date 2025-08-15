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
    //location
    private Long capacity;
    private String description;
    private String price;
    private String deadline;

    private Long total;

    private Long price;


    @ManyToOne
    @JoinColumn(nullable=false)
    private Location location;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user; //방장

    @OneToMany(mappedBy="gather", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();


}
