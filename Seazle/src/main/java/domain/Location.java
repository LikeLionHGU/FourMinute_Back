package domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="Location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Review> reviews= new ArrayList<>();
}
