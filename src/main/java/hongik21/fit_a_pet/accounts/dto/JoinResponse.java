package hongik21.fit_a_pet.accounts.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinResponse {

    private Long id;
    private String name;
    private String nickname;
    private LocalDateTime createdAt;

}
