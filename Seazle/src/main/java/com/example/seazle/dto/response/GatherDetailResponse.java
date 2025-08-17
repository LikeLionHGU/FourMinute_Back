package com.example.seazle.dto.response;

import com.example.seazle.domain.Gather;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatherDetailResponse {

    private Long id;
    private String title;
    private String sport;
    private String time;
    private String location;
    private Long capacity;
    private String description;
    private String originalPrice;
    private String currentPrice;
    private String deadline;
    private String makerImage;
    private String makerName;
    private String makerContact;
    private String makerStatement;
    private Long total;

    public static GatherDetailResponse gatherDetailResponse(Gather gather) {
        return GatherDetailResponse.builder()
                .id(gather.getId())
                .title(gather.getTitle())
                .sport(gather.getSport())
                .time(gather.getTime())
                .location(gather.getLocation().getName())
                .capacity(gather.getCapacity())
                .description(gather.getDescription())
                .originalPrice(gather.getOriginalPrice())
                .currentPrice(gather.getCurrentPrice())
                .deadline(gather.getDeadline())
                .makerName(gather.getUser().getName())
                .makerImage(gather.getUser().getImageUrl())
                .makerContact(gather.getMaker())
                .makerStatement(gather.getUser().getStatement())
                .total(gather.getTotal())
                .build();
    }

}
