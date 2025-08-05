package hongik21.fit_a_pet.trails.domain;

import hongik21.fit_a_pet.accounts.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성
    private Long recordId;

    private LocalDate walkDate;
    private LocalTime walkStart;
    private LocalTime walkEnd;

    private Float distance;
    private Integer rating;
    private String memo;

    private Integer petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    // 위치 정보
    private String address;
}
