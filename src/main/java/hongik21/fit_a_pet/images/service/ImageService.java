package hongik21.fit_a_pet.images.service;

import hongik21.fit_a_pet.global.config.AwsS3Client;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AwsS3Client awsS3Client;

    public String uploadFile(MultipartFile image) {

        UUID imageId = UUID.randomUUID();
        String filePath = "images/" + imageId + ".jpg";

        try{
            return awsS3Client.uploadFile(image, filePath);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }
}
