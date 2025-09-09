package hongik21.fit_a_pet.posts.dto;

import hongik21.fit_a_pet.posts.domain.PostCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponse {
    private Long postId;
    private PostCategoryType postCategory;
    private String postTitle;
    private LocalDateTime postDate;
    private String nickname;
}
