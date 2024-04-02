package shop.mtcoding.blog.model.file;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class FileInfoRequest {
    @Data
    public static class UploadDTO{
        private String fileName;
        private String encodedData;

        public UploadDTO( String fileName, String encodedData) {
            this.fileName = fileName;
            this.encodedData = encodedData;
        }
    }
}