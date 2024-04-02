package shop.mtcoding.blog.model.file;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.blog._core.util.ApiUtil;

@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;

    @PostMapping("/api/upload")
    public ResponseEntity<?> upload(@RequestBody FileInfoRequest.UploadDTO reqDTO){
        FileResponse.UploadSuccessDTO respDTO = fileService.upload(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}

