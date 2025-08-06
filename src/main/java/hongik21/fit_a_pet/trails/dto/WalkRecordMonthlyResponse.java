package hongik21.fit_a_pet.trails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRecordMonthlyResponse {

    private Long recordId;
    private String walkDate;
    private String walkStart;
    private String walkEnd;
    private String formattedDuration;
    private Float distance;
    private Integer petId;
    private String address;
    private Integer rating;
    private String memo;
}
