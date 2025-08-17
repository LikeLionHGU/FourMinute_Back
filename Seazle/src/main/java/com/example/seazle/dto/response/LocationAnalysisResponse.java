package com.example.seazle.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationAnalysisResponse {

    private String content;
    private Long count;

    public static LocationAnalysisResponse locationAnalysisResponse(String content, Long count) {
        return LocationAnalysisResponse.builder()
                .content(content)
                .count(count)
                .build();
    }

}
