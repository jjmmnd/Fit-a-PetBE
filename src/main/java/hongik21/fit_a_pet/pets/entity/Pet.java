package hongik21.fit_a_pet.pets.entity;

import hongik21.fit_a_pet.accounts.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, name = "gender_type")
    @Enumerated(EnumType.STRING)
    private PetGenderType gender;

    private Double weight;

    @Column(nullable = false, name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetType type;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member member;

    /** 펫 가입일*/
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    /** 펫 삭제일*/
    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

    /** 성향 리스트 조회용*/
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PetTraitRelation> traits = new ArrayList<>();
}
