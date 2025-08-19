package com.example.seazle.dto.response;

import com.example.seazle.domain.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatherLocationResponse {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String time;

    public static GatherLocationResponse gatherLocationResponse(Location location) {
        return GatherLocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .description(location.getAiReview())
                .address(location.getAddress())
                .time(location.getTime())
                .build();
    }

}
