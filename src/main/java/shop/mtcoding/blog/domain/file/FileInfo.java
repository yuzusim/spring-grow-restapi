package shop.mtcoding.blog.domain.file;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fileinfo_tb")
@Data
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String imgFilename; // 파일 패스
}