package com.example.seazle.domain;

import com.example.seazle.service.AnalysisService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String oneLine;
    private String description;
    private String address;
    private String time;
    private String number;
    private String priceImgUrl;
    private String socialLink;
    private Long longitude;
    private Long latitude;
    private Long reviewCount;
    private String reserveLink;
    private Double score;
    private Long price1;
    private Long price2;
    private String region;
    private String aiReview;
    private String goodPart;
    private String badPart;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> analysis= Arrays.asList(0L,0L,0L);

    @OneToMany(mappedBy="location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy="location", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Gather> gathers = new ArrayList<>();


    public void updateScore(Double newScore) {
        Double sum = this.score*this.reviewCount+newScore;
        this.reviewCount++;
        this.score = sum/this.reviewCount;
    }

    public void updateAnalysis(String content) {

        List<String> topWords = AnalysisService.reviewAnalysis(content);
        for(String word : topWords){
            for(int i=0;i<AnalysisService.keyWords.length;i++){
                if(word.equals(AnalysisService.keyWords[i])){
                    this.analysis.set(i,this.analysis.get(i)+1);
                }
            }
        }
    }


}


