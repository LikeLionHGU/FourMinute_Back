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
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String sport;
    private String description;
    private String address;
    private String time;
    private String number;
    private String priceImgUrl;
    private String socialLink;
    private String aiReview;
    private Long longitude;
    private Long latitude;
    private Long reviewCount;
    private String reserveLink;
    private Long score;
    private Long price;
    private String region;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> analysis;

    @OneToMany(mappedBy="location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Review> reviews= new ArrayList<>();

    @OneToMany(mappedBy="location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Gather> gathers= new ArrayList<>();


}


