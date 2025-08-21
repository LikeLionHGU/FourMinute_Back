package com.example.seazle.dto.response;

import com.example.seazle.domain.Gather;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatherResponse {

    private Long id;
    private String title;
    private String sport;
    private String time;
    private String location;
    private Long capacity;
    private Long total;

    public static GatherResponse gatherResponse(Gather gather, Long total) {
        return GatherResponse.builder()
                .id(gather.getId())
                .title(gather.getTitle())
                .sport(gather.getSport())
                .time(gather.getTime())
                .location(gather.getLocation().getName())
                .capacity(gather.getCapacity())
                .total(total)
                .build();
    }


}
