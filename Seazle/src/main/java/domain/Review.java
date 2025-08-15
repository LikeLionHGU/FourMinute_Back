package domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Long score;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Location location;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;

    public static Review review(String content, Long score, Location location, User user) {
        return Review.builder()
                .content(content)
                .score(score)
                .location(location)
                .user(user)
                .build();
    }

}
