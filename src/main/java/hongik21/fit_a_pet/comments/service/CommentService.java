package hongik21.fit_a_pet.comments.service;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.comments.dto.CommentInfo;
import hongik21.fit_a_pet.comments.dto.CommentRequest;
import hongik21.fit_a_pet.comments.entity.Comment;
import hongik21.fit_a_pet.comments.repository.CommentRepository;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import hongik21.fit_a_pet.petposts.domain.PetPost;
import hongik21.fit_a_pet.petposts.dto.PetPostDetailResponse;
import hongik21.fit_a_pet.posts.domain.Post;
import hongik21.fit_a_pet.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
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

    public CommentInfo updateComment(Member member, Long postId, Long commentId, CommentRequest request) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ApplicationException(CustomErrorCode.COMMENT_NOT_FOUND));

        // 포스트가 존재하는지 확인
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ApplicationException(CustomErrorCode.POST_NOT_FOUND));

        // 해당 포스트에 달린 댓글이 맞는지 확인
        if (!comment.getPost(). getPostId().equals(post.getPostId())) {
            throw new ApplicationException(CustomErrorCode.FORBIDDEN);
        }

        // 댓글 작성자와 일치하는지 확인
        if(!comment.getMember().getId().equals(member.getId()))
            throw new ApplicationException(CustomErrorCode.FORBIDDEN);

        comment.update(request.getComment());

        return CommentInfo.builder()
                .commentId(commentId)
                .comment(comment.getComment())
                .lastModified(comment.getModifiedAt())
                .build();
    }

    public void deleteComment(Member member, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ApplicationException(CustomErrorCode.COMMENT_NOT_FOUND));

        // 댓글 작성자와 일치하는지 확인
        if(!comment.getMember().getId().equals(member.getId()))
            throw new ApplicationException(CustomErrorCode.FORBIDDEN);

        commentRepository.delete(comment);
    }

    public List<CommentInfo> getComments(Long postId) {
        // 포스트가 존재하는지 확인
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ApplicationException(CustomErrorCode.POST_NOT_FOUND));

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(comment -> CommentInfo.builder()
                        .comment(comment.getComment())
                        .lastModified(comment.getModifiedAt())
                        .commentId(comment.getId())
                        .build()).toList();

    }
}
