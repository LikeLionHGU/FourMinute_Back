package com.example.seazle.dto.response;

import com.example.seazle.domain.Review;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationReviewResponse {

    private String name;
    private String content;
    private Double score;
    private String time;

    public static LocationReviewResponse locationReviewResponse(Review review) {
        return LocationReviewResponse.builder()
                .name(review.getUser().getName())
                .content(review.getContent())
                .score(review.getScore())
                .time(review.getCreated().toString())
                .build();
    }

    
}
