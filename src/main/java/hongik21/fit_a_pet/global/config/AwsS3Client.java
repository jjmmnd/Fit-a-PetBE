package hongik21.fit_a_pet.global.config;

import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
public class AwsS3Client {
    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file, String fileName) throws BadRequestException {
        try (InputStream is = file.getInputStream()) {
            S3Resource upload = s3Template.upload(bucketName, fileName, is);
            return upload.getURL().toString();
        } catch (IOException | S3Exception e) {
            throw new BadRequestException("파일 업로드에 실패했습니다.", e);
        }
    }
}
