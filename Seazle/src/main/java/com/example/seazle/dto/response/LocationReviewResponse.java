package com.example.seazle.dto.response;

import com.example.seazle.domain.Review;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime created = review.getCreated();
        Duration duration = Duration.between(created, now);

        return LocationReviewResponse.builder()
                .name(review.getUser().getName())
                .content(review.getContent())
                .score(review.getScore())
                .time(String.valueOf((int)duration.toDays()))
                .build();
    }

    
}
