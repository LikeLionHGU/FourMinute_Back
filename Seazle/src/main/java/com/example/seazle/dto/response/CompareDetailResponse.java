package com.example.seazle.dto.response;

import com.example.seazle.domain.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompareDetailResponse {

    private Long id;
    private String name;
    private String imgUrl;
    private Double score;
    private String region;
    private Long price1;
    private Long price2;
    private String priceImgUrl;
    private String goodPart;
    private String badPart;
    private String aiReview;

    public static CompareDetailResponse compareDetailResponse(Location location) {
        return CompareDetailResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .imgUrl(location.getImageUrl())
                .score(location.getScore())
                .region(location.getRegion())
                .price1(location.getPrice1())
                .price2(location.getPrice2())
                .priceImgUrl(location.getPriceImgUrl())
                .aiReview(location.getAiReview())
                .goodPart(location.getGoodPart())
                .badPart(location.getBadPart())
                .build();
    }


}
