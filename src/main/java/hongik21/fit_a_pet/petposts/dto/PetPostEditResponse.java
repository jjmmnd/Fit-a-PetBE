package hongik21.fit_a_pet.petposts.dto;

import hongik21.fit_a_pet.petposts.domain.PetPostCategoryType;
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
public class PetPostEditResponse {

    private Long petPostId;
    private String petPostTitle;
    private String petPostContent;
    private PetPostCategoryType petPostCategory;
    private LocalDateTime petPostDate;
    private LocalDateTime petPostEditDate;

    private String nickname;
    private String petName;
}
