package hongik21.fit_a_pet.posts.repository;

import hongik21.fit_a_pet.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    // postId, email로 본인이 맞는지 확인하는 용도
    Optional<Post> findByPostIdAndMemberId_Email(Long postId, String email);

    // 단건 조회
    Optional<Post> findByPostId(Long postId);

}
