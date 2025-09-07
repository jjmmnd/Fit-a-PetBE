package hongik21.fit_a_pet.images.controller;

import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = imageService.uploadFile(file);
        return CommonResponse.onSuccess(null, "S3 버킷에 이미지 업로드 성공");
    }
}
