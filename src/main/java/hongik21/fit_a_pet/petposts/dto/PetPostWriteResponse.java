package hongik21.fit_a_pet.petposts.dto;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.petposts.domain.PetPostCategoryType;
import hongik21.fit_a_pet.pets.entity.Pet;
import hongik21.fit_a_pet.pets.entity.PetGenderType;
import hongik21.fit_a_pet.pets.entity.PetTrait;
import hongik21.fit_a_pet.pets.entity.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetPostWriteResponse {

    private Long petPostId;
    private String petPostTitle;
    private String petPostContent;
    private PetPostCategoryType petPostCategory;
    private LocalDateTime petPostDate;

    private String nickname;
    private String petName;
    private Integer petAge;
    private PetGenderType petGender;
    private PetType petType;
    private String petTraits;

    private String imageUrl;
}
