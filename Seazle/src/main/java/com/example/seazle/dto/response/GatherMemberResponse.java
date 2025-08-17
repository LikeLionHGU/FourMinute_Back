package com.example.seazle.dto.response;

import com.example.seazle.domain.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatherMemberResponse {

    private String name;
    private String imageUrl;
    private String statement;

    public static GatherMemberResponse gatherMemberResponse(User user) {
        return GatherMemberResponse.builder()
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .statement(user.getStatement())
                .build();
    }

}
