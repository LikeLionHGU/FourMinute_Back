package com.example.seazle.dto.response;

import com.example.seazle.domain.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {

    private Long id;
    private String name;
    private String thumbnail;
    private String sport;
    private String description;
    private String region;
    private Double score;
    private Long reviewCount;

    public static LocationResponse locationResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .thumbnail(location.getImageUrl())
                .sport(location.getSport())
                .description(location.getAiReview())
                .region(location.getRegion())
                .score(location.getScore())
                .reviewCount((long)location.getReviews().size())
                .build();
    }


}
