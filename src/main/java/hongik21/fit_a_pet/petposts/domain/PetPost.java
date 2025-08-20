package hongik21.fit_a_pet.petposts.domain;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.pets.entity.Pet;
import hongik21.fit_a_pet.posts.domain.PostCategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petPostId;

    @Enumerated(EnumType.STRING)
    private PetPostCategoryType petPostCategory;

    private String petPostTitle;
    private String petPostContent;
    private LocalDateTime petPostDate;
    private LocalDateTime petPostEditDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // 펫 성향 필요
}
