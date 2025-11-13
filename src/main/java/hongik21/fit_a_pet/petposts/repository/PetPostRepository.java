package hongik21.fit_a_pet.petposts.repository;


import hongik21.fit_a_pet.petposts.domain.PetPost;
import hongik21.fit_a_pet.petposts.dto.PetPostDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetPostRepository extends JpaRepository<PetPost, Long>{

    Optional<PetPost> findByPetPostIdAndMemberId_Email(Long petPostId, String email);

    Optional<PetPost> findByPetPostId(Long petPostId);

    List<PetPost> findByMemberId(Long memberId);

    // N+1 해결
    @Query("SELECT p FROM PetPost p " +
            "JOIN FETCH p.member " +
            "JOIN FETCH p.pet pet " +
            "LEFT JOIN FETCH pet.traits " +
            "WHERE p.member.id = :memberId")
    List<PetPost> findByMemberIdWithDetails(@Param("memberId") Long memberId);
}
