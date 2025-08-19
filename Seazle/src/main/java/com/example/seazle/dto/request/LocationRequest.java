package com.example.seazle.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {

    private String name;
    private String imageUrl;
    private String sport;
    private String description;
    private String address;
    private String time;
    private String number;
    private String priceImgUrl;
    private String socialLink;
    private Long longitude;
    private Long latitude;
    private String reserveLink;
    private Double score;
    private Long price1;
    private Long price2;
    private String region;
    private String aiReview;
    private String goodPart;
    private String badPart;


}
