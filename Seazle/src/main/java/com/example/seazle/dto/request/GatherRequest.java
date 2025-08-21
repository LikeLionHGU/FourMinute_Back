package com.example.seazle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GatherRequest {

    private Long locationId;
    private Long UserId;
    private String title;
    private String sport;
    private String time;
    private Long capacity;
    private String description;
    private String originalPrice;
    private String currentPrice;
    private String deadline;
    private String maker;

}
