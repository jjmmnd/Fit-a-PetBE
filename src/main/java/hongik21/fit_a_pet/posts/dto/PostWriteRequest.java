package hongik21.fit_a_pet.posts.dto;

import hongik21.fit_a_pet.posts.domain.PostCategoryType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostWriteRequest {

    private String postTitle;
    private PostCategoryType postCategory;
    private String postContent;
}
