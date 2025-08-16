package dto.response;

import domain.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompareDetailResponse { //공사중

    private Long id;
    private String name;
    private String imgUrl;
    private Long score;
    private String region;
    private String price;
    private String priceImgUrl;
    private String aiReview;

    public static CompareDetailResponse compareDetailResponse(Location location) {
        return CompareDetailResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .imgUrl(location.getImageUrl())
                .score(location.getScore())
                .region(location.getRegion())
                .price(location.getPrice())
                .priceImgUrl(location.getPriceImgUrl())
                .aiReview(location.getAiReview())
                .build();
    }


}
