package hongik21.fit_a_pet.global.jwt;

import hongik21.fit_a_pet.accounts.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 유저의 인증을 담은 객체
 */
@RequiredArgsConstructor
public class CustomMemberDetails implements UserDetails {

    private final Member member;

    /**
     * Spring Security 가 사용자의 역할을 판단하기 위해 사용할 함수
     * @return Spring Security 에서 사용자의 권한 정보를 나타내는 객체들의 컬렉션 (권한 정보 반환)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + member.getMemberType().toString());

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
//        return member.getName();
        return member.getEmail();
    }

}
