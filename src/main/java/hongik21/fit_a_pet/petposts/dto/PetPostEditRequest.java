package hongik21.fit_a_pet.petposts.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PetPostEditRequest {

    private String petPostTitle;
    private LocalDateTime petPostEditDate;
    private String petPostContent;
    private String imageUrl;
}
