package hongik21.fit_a_pet.comments.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentInfo {
    private Long commentId;
    private String comment;
    private LocalDateTime lastModified;
}
