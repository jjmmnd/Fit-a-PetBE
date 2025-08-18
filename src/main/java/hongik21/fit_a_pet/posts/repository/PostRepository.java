package hongik21.fit_a_pet.posts.repository;

import hongik21.fit_a_pet.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findByPostIdAndMemberId_Email(Long postId, String email);
}
