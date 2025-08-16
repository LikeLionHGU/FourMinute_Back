package dto.response;

import domain.Location;
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
                .description(location.getDescription())
                .address(location.getAddress())
                .time(location.getTime())
                .build();
    }

}
