package hongik21.fit_a_pet.petposts.dto;

import hongik21.fit_a_pet.petposts.domain.PetPostCategoryType;
import hongik21.fit_a_pet.pets.entity.PetGenderType;
import hongik21.fit_a_pet.pets.entity.PetType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PetPostWriteRequest {

    private Long petId;
    private String petPostTitle;
    private PetPostCategoryType petPostCategory;
    private String petPostContent;
    private LocalDateTime petPostDate;

    private String imageUrl;
}
