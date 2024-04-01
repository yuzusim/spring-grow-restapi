package shop.mtcoding.blog.model.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.blog._core.util.ApiUtil;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;

    @PostMapping("/resume/save")
    public ResponseEntity<?> save(@RequestBody ResumeRequest.SaveDTO reqDTO){
        resumeService.save(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }
}
