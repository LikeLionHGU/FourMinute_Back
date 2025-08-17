package com.example.seazle.dto.response;

import com.example.seazle.domain.Comment;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatherCommentResponse {

    private String name;
    private String content;
    private String time;

    public static GatherCommentResponse gatherCommentResponse(Comment comment) {
        return GatherCommentResponse.builder()
                .name(comment.getUser().getName())
                .content(comment.getContent())
                .time(comment.getCreated().toString())
                .build();
    }


}
