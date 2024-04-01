package shop.mtcoding.blog.model.file;

import lombok.Data;

public class FileResponse {

    @Data
    public static class UploadSuccessDTO {
        private String fileName;
        private String filePath;

        public UploadSuccessDTO(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath;
        }
    }
}
