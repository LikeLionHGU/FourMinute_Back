package com.example.seazle.dto.response;

import com.example.seazle.domain.Comment;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime created = comment.getCreated();
        Duration duration = Duration.between(created, now);
        return GatherCommentResponse.builder()
                .name(comment.getUser().getName())
                .content(comment.getContent())
                .time(String.valueOf((int)duration.toDays()))
                .build();
    }


}
