package hongik21.fit_a_pet.comments.service;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.comments.dto.CommentInfo;
import hongik21.fit_a_pet.comments.dto.CommentRequest;
import hongik21.fit_a_pet.comments.entity.Comment;
import hongik21.fit_a_pet.comments.repository.CommentRepository;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import hongik21.fit_a_pet.posts.domain.Post;
import hongik21.fit_a_pet.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentInfo saveComment(Member member, Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ApplicationException(CustomErrorCode.POST_NOT_FOUND));

        Comment newComment = Comment.builder()
                .comment(request.getComment())
                .post(post)
                .member(member)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        Comment savedComment = commentRepository.save(newComment);

        return CommentInfo.builder()
                .commentId(savedComment.getId())
                .comment(savedComment.getComment())
                .lastModified(savedComment.getModifiedAt())
                .build();
    }
}
