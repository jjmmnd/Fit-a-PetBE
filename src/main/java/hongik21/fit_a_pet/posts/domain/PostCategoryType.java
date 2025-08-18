package hongik21.fit_a_pet.posts.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostCategoryType {
    GeneralPost("자유게시판"),
    InfoPost("정보게시판");

    private final String description;
}
