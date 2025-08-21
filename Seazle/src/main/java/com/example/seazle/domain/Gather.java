package com.example.seazle.domain;

import com.example.seazle.dto.request.GatherRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ManyToOne
    @JoinColumn(nullable=false)
    private Location location;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;

    @OneToMany(mappedBy="gather", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();


    public static Gather gather(GatherRequest gatherRequest, User user, Location location) {
        return Gather.builder()
                .title(gatherRequest.getTitle())
                .sport(gatherRequest.getSport())
                .time(gatherRequest.getTime())
                .capacity(gatherRequest.getCapacity())
                .description(gatherRequest.getDescription())
                .originalPrice(gatherRequest.getOriginalPrice())
                .currentPrice(gatherRequest.getCurrentPrice())
                .deadline(gatherRequest.getDeadline())
                .maker(gatherRequest.getMaker())
                .location(location)
                .user(user)
                .build();
    }

}
