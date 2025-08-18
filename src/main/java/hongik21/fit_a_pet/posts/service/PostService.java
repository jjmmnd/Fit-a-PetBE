package hongik21.fit_a_pet.posts.service;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.accounts.repository.MemberRepository;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import hongik21.fit_a_pet.posts.domain.Post;
import hongik21.fit_a_pet.posts.dto.PostEditRequest;
import hongik21.fit_a_pet.posts.dto.PostEditResponse;
import hongik21.fit_a_pet.posts.dto.PostWriteRequest;
import hongik21.fit_a_pet.posts.dto.PostWriteResponse;
import hongik21.fit_a_pet.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 1. 게시글 작성
    public PostWriteResponse write(String email, PostWriteRequest request){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.MEMBER_NOT_FOUND));


        Post post = Post.builder()
                .memberId(member)
                .postTitle(request.getPostTitle())
                .postContent(request.getPostContent())
                .postCategory(request.getPostCategory())
                .postDate(request.getPostDate())
                .build();

        try {
            Post res = postRepository.save(post);
            return PostWriteResponse.builder()
                    .postId(res.getPostId())
                    .postTitle(res.getPostTitle())
                    .postCategory(res.getPostCategory())
                    .postDate(res.getPostDate())
                    .postContent(res.getPostContent())
                    .nickname(res.getMemberId().getNickname())
                    .build();
        } catch(Exception e){
            throw new ApplicationException(CustomErrorCode.POST_WRITE_FAILED);
        }
    }

    // 2. 게시물 수정
    public PostEditResponse editPost(PostEditRequest request, Long postId, String email){
        Post post = postRepository.findByPostIdAndMemberId_Email(postId, email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.POST_NOT_FOUND));

        post.setPostTitle(request.getPostTitle());
        post.setPostContent(request.getPostContent());
        post.setPostEditDate(request.getPostEditDate());

        Post updatedPost = postRepository.save(post);

        try {
            return PostEditResponse.builder()
                    .postId(updatedPost.getPostId())
                    .postCategory(updatedPost.getPostCategory())
                    .postTitle(updatedPost.getPostTitle())
                    .postContent(updatedPost.getPostContent())
                    .postEditDate(updatedPost.getPostEditDate())
                    .nickname(updatedPost.getMemberId().getNickname())
                    .build();
        } catch (Exception e) {
            throw new ApplicationException((CustomErrorCode.POST_NOT_FOUND));
        }
    }

    // 3. 게시물 삭제
    public void deletePost(Long postId, String email){
        Post post = postRepository.findByPostIdAndMemberId_Email(postId, email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.POST_NOT_FOUND));

        try {
            postRepository.delete(post);
        } catch (Exception e) {
            throw new ApplicationException(CustomErrorCode.POST_NOT_FOUND);
        }
    }


}
