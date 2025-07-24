package hongik21.fit_a_pet.trails.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
public class WalkRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성
    private Integer record_id;

    private LocalDate walk_date;

    private LocalTime walk_start;
    private LocalTime walk_end;
    private Float distance;
    private Integer rating;
    private String memo;

    private Integer pet_id;
    private Integer user_id;

    // 기본 생성자
    public WalkRecord (){}

}
