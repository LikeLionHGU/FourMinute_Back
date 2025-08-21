package com.example.seazle.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Long secrete;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Gather gather;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;


    public static Comment comment(String content, Gather gather, User user) {
        return Comment.builder()
                .content(content)
                .gather(gather)
                .user(user)
                .build();
    }


}
