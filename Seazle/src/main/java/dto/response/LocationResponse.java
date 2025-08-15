package dto.response;

import domain.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {

    private Long id;
    private String name;
    private String sport;
    private String description;
    private String region;
    private Long score;
    private Long reviewCount;

    public static LocationResponse locationResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .sport(location.getSport())
                .description(location.getDescription())
                .region(location.getRegion())
                .score(location.getScore())
                .reviewCount(location.getReviewCount())
                .build();
    }


}
