package com.example.seazle.domain;

import com.example.seazle.dto.request.LocationRequest;
import com.example.seazle.service.AnalysisService;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String sport;
    @Column(length = 1000)
    private String description;
    private String address;
    private String time;
    private String number;
    private String priceImgUrl;
    private String socialLink;
    private Double longitude;
    private Double latitude;
    private String reserveLink;
    private Double score;
    private Long price1;
    private Long price2;
    private String region;
    private String aiReview;
    private String goodPart;
    private String badPart;


    @OneToMany(mappedBy="location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy="location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Gather> gathers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn
    private List<Long> analysis= new ArrayList<>();


    public static Location location(LocationRequest locationRequest) {
        return Location.builder()
                .analysis(Arrays.asList(0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L))
                .name(locationRequest.getName())
                .imageUrl(locationRequest.getImageUrl())
                .sport(locationRequest.getSport())
                .description(locationRequest.getDescription())
                .address(locationRequest.getAddress())
                .time(locationRequest.getTime())
                .number(locationRequest.getNumber())
                .priceImgUrl(locationRequest.getPriceImgUrl())
                .socialLink(locationRequest.getSocialLink())
                .longitude(locationRequest.getLongitude())
                .latitude(locationRequest.getLatitude())
                .reserveLink(locationRequest.getReserveLink())
                .score(locationRequest.getScore())
                .price1(locationRequest.getPrice1())
                .price2(locationRequest.getPrice2())
                .region(locationRequest.getRegion())
                .aiReview(locationRequest.getAiReview())
                .goodPart(locationRequest.getGoodPart())
                .badPart(locationRequest.getBadPart())
                .build();
    }


    public void updateScore(Double newScore) {
        Double sum = this.score*(this.reviews.size()-1)+newScore;
        this.score = sum/this.reviews.size();
    }

    public void updateAnalysis(String content) {
        List<String> topWords = AnalysisService.reviewAnalysis(content);
        for(String word : topWords){
            for(int i=0;i<this.analysis.size();i++){
                if(word.equals(AnalysisService.keyWords[i])){
                    this.analysis.set(i,this.analysis.get(i)+1);
                }
            }
        }
    }

    public void updateAiReview(String content) {
        this.aiReview = content;
    }


}


