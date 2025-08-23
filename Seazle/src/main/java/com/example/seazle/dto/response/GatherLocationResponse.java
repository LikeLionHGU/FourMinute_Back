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
    private String image;

    public static GatherLocationResponse gatherLocationResponse(Location location) {
        return GatherLocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .description(location.getOneLine())
                .address(location.getAddress())
                .time(location.getTime())
                .image(location.getImageUrl())
                .build();
    }

}
