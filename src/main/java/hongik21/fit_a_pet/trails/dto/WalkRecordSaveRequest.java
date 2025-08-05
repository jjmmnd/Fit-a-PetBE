package hongik21.fit_a_pet.trails.dto;
import lombok.Getter;

@Getter
public class WalkRecordSaveRequest {

    // 문자열로 받아서 LocalTime, LocalDate로 변경
    private String walkDate;
    private String walkStart;
    private String walkEnd;

    private Float distance;

    private Integer petId;

    private String address;
    private Integer rating;
    private String memo;
}
