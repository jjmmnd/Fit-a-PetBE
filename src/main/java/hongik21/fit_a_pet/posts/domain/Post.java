package hongik21.fit_a_pet.posts.domain;

import hongik21.fit_a_pet.accounts.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Enumerated(EnumType.STRING)
    private PostCategoryType postCategory;

    private String postTitle;
    private String postContent;
    private LocalDateTime postDate;
    private LocalDateTime postEditDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_nickname")
    private Member nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;



}
