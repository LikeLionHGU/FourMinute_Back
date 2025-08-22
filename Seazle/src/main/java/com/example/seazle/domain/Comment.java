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

    @Column(length = 1000)
    private String content;
    private Long secrete;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Gather gather;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;


    public static Comment comment(String content, Gather gather, User user, Long secrete) {
        return Comment.builder()
                .content(content)
                .gather(gather)
                .user(user)
                .secrete(secrete)
                .build();
    }


}
