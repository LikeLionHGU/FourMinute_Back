package dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationAnalysisResponse {

    private String content;
    private Long count;

}
