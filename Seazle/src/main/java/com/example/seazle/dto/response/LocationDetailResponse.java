package com.example.seazle.dto.response;

import com.example.seazle.domain.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDetailResponse {

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
    private Double longitude;
    private Double latitude;
    private Long reviewCount;
    private String reserveLink;

    public static LocationDetailResponse locationDetailResponse(Location location) {
        return LocationDetailResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .imageUrl(location.getImageUrl())
                .sport(location.getSport())
                .description(location.getDescription())
                .address(location.getAddress())
                .time(location.getTime())
                .number(location.getNumber())
                .priceImgUrl(location.getPriceImgUrl())
                .socialLink(location.getSocialLink())
                .aiReview(location.getAiReview())
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .reviewCount((long)location.getReviews().size())
                .reserveLink(location.getReserveLink())
                .build();
    }


}
