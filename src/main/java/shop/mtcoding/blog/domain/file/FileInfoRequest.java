package shop.mtcoding.blog.domain.file;

import lombok.Data;

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