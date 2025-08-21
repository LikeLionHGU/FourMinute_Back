package com.example.seazle.dto.response;

import com.example.seazle.domain.Gather;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationGatherResponse {

    private Long id;
    private String title;
    private String time;
    private String location;
    private Long capacity;
    private Long total;

    public static LocationGatherResponse locationGatherResponse(Gather gather, Long total) {
        return LocationGatherResponse.builder()
                .id(gather.getId())
                .title(gather.getTitle())
                .time(gather.getTime())
                .location(gather.getLocation().getName())
                .capacity(gather.getCapacity())
                .total(total)
                .build();
    }


}
