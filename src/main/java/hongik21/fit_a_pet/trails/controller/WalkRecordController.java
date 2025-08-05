package hongik21.fit_a_pet.trails.controller;

import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.trails.dto.*;
import hongik21.fit_a_pet.trails.service.WalkRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trails")
public class WalkRecordController {

    private final WalkRecordService walkRecordService;

    @PostMapping()
    public CommonResponse<WalkRecordSaveResponse> registerTrailRecord(
            @RequestBody WalkRecordSaveRequest request) throws ApplicationException {
        // JWT 필터에서 이미 인증했으므로 SecurityContext에서 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // userName을 email로 설정했었음

        WalkRecordSaveResponse response = walkRecordService.register(email, request);
        return CommonResponse.onSuccess(response,"산책 기록이 성공했습니다.");
    }



    @GetMapping()
    public CommonResponse<List<WalkRecordMonthlyResponse>> monthlyTrailRecord(
            @RequestParam int year,
            @RequestParam int month
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // userName을 email로 설정했었음

        List<WalkRecordMonthlyResponse> response = walkRecordService.getMonthlyRecords(email,year,month);
        return CommonResponse.onSuccess(response,"산책 기록 월별 조회에 성공했습니다.");
    }



    @GetMapping("/{recordId}")
    public CommonResponse<WalkRecordDetailResponse> trailRecordDetail(@PathVariable Long recordId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // userName을 email로 설정했었음

        WalkRecordDetailResponse response = walkRecordService.getRecordsDetail(recordId,email);
        return CommonResponse.onSuccess(response,"산책 기록 단건 조회에 성공했습니다.");
    }



    @PutMapping("/{recordId}")
    public CommonResponse<WalkRecordEditResponse> editTrailRecord(
            @RequestBody WalkRecordEditRequest request,
            @PathVariable Long recordId) throws ApplicationException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        WalkRecordEditResponse response = walkRecordService.editRecord(request, recordId,email);
        return CommonResponse.onSuccess(response,"산책 기록 수정에 성공했습니다.");
    }


    @DeleteMapping("/{recordId}")
    public CommonResponse<?> deleteTrailRecord(@PathVariable Long recordId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        walkRecordService.deleteRecord(recordId,email);
        return CommonResponse.onSuccess(null,"산책 기록 삭제에 성공했습니다");
    }

    @GetMapping("/doTest")
    public String doTest(){
        System.out.println("test");
        return "테스트 성공";
    }
}
