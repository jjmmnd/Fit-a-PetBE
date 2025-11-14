package hongik21.fit_a_pet.comments.repository;

import hongik21.fit_a_pet.comments.entity.Comment;
import hongik21.fit_a_pet.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
