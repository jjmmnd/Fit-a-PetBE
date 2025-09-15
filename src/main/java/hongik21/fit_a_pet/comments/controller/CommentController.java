package hongik21.fit_a_pet.comments.controller;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.accounts.service.MemberService;
import hongik21.fit_a_pet.comments.dto.CommentInfo;
import hongik21.fit_a_pet.comments.dto.CommentRequest;
import hongik21.fit_a_pet.comments.service.CommentService;
import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.pets.dto.PetInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {

    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("/{post_id}/comments")
    public CommonResponse<CommentInfo> createComment(
            @PathVariable("post_id") Long postId, @RequestBody CommentRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Member member = memberService.getMemberByEmail(email);
        CommentInfo response = commentService.saveComment(member, postId, request);
        return CommonResponse.onSuccess(response, "댓글 생성 성공");
    }

    @PutMapping("/{post_id}/comments/{comment_id}")
    public CommonResponse<CommentInfo> updateComment(
        @PathVariable("post_id") Long postId,
        @PathVariable("comment_id") Long commentId,
        @RequestBody CommentRequest request
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Member member = memberService.getMemberByEmail(email);
        CommentInfo response = commentService.updateComment(member, postId, commentId, request);
        return CommonResponse.onSuccess(response, "댓글 수정 성공");
    }
}
