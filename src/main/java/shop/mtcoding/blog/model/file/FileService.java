package shop.mtcoding.blog.model.file;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    private final HttpSession session;
    private final UserJPARepository userJPARepo;

    @Transactional
    public FileResponse.UploadSuccessDTO upload(FileInfoRequest.UploadDTO reqDTO){
        String encodedData = reqDTO.getEncodedData().split(",")[1];
        //1. 데이터전달
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);

        //2. 파일저장 위치 설정 및 파일저장 (UUID)
        String imgFilename = UUID.randomUUID()+"_"+reqDTO.getFileName();
        Path imgPath = Paths.get("./upload/"+imgFilename);
        try {
            // 이미지 파일 저장
            Files.write(imgPath, decodedBytes);

            User sessionUser = (User) session.getAttribute("sessionUser");
            User newSessionUser = userJPARepo.findById(sessionUser.getId())
                    .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
            newSessionUser.setImgFileName(imgFilename);

            return new FileResponse.UploadSuccessDTO(imgFilename, imgPath.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
