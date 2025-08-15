package dto.response;

import domain.Gather;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatherDetailResponse {

    private Long id;
    private String name;
    //공사중

    public static GatherDetailResponse gatherDetailResponse(Gather gather) {

    }

}
