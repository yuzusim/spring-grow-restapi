package shop.mtcoding.blog.domain.file;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserJPARepository;

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
        //1. 데이터전달
        byte[] decodedBytes = Base64.getDecoder().decode(reqDTO.getEncodedData());

        //2. 파일저장 위치 설정 및 파일저장 (UUID)
        String imgFilename = UUID.randomUUID()+"_"+reqDTO.getFileName();
        Path imgPath = Paths.get("./upload/"+imgFilename);
        try {
            // 이미지 파일 저장
            Files.write(imgPath, decodedBytes);

            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
            User newSessionUser = userJPARepo.findById(sessionUser.getId())
                    .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
            newSessionUser.setImgFileName(imgFilename);

            return new FileResponse.UploadSuccessDTO(imgFilename, imgPath.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
