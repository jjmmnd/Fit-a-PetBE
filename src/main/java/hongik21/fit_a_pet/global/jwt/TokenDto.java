package hongik21.fit_a_pet.global.jwt;

import lombok.Builder;

@Builder
public record TokenDto(String accessToken, String refreshToken) {
}
