package hongik21.fit_a_pet.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "profile_url", columnDefinition = "text")
    private String profileUrl;

    private MemberType memberType;
    private String residence;

    @CreatedDate
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;
}
