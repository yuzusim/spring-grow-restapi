package shop.mtcoding.blog.domain.comp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import shop.mtcoding.blog.domain.skill.SkillRequest;
import shop.mtcoding.blog.domain.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompRequest {

    @Data
    public static class JobsViewDTO {
        private Integer id;
        private Integer userId;
        private String compName;
        private String title;
        private String task;
        private String career;
        private List<SkillRequest.JobSkillDTO> skillList = new ArrayList<>();
        private Integer number;
    }

    @Data
    public static class ResumeViewDTO {
        private Integer id;
        private Integer userId;
        private String myName;
        private String title;
        private String edu;
        private String career;
        private String area;
        private String imgFileName;
        private List<SkillRequest.CompSkillDTO> skillList = new ArrayList<>();
    }

    @Data
    public static class UserApplyDTO {
        private Integer id;
        private Integer resumeId;
        private String name;
        private String title;
        private String career;
        private List<SkillRequest.JobSkillDTO> skillList = new ArrayList<>();
    }

    @Data
    public static class UpdateDTO {
        @Size(max = 10, message = "이름은 10글자를 넘길 수 없습니다.")
        @NotEmpty(message = "이름을 작성하여 주십시오.")
        private String myName;

        @Size(max = 20, message = "비밀번호는 30글자를 넘길 수 없습니다.")
        @NotEmpty(message = "비밀번호를 작성하여 주십시오.")
        private String password;

        @Size(max = 20, message = "전화번호는 20글자를 넘길 수 없습니다.")
        @NotEmpty(message = "전화번호를 작성하여 주십시오.")
        private String phone;

        @Size(max = 100, message = "주소는 100글자를 넘길 수 없습니다.")
        @NotEmpty(message = "주소를 작성하여 주십시오.")
        private String address;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;

        @Size(max = 12, message = "사업자 등록 번호는 12글자를 넘길 수 없습니다.")
        @NotEmpty(message = "사업자 등록 번호를 작성하여 주십시오.")
        private String businessNumber;

        @Size(max = 12, message = "사업자 등록 번호는 12글자를 넘길 수 없습니다.")
        @NotEmpty(message = "사업자 등록 번호를 작성하여 주십시오.")
        private String compName;

        @Size(max = 50, message = "홈페이지 주소는 50글자를 넘길 수 없습니다.")
        private String homepage;
    }

    @Data
    public static class CompJoinDTO {
        private Integer id;
        @Size(max = 30, message = "이메일은 30글자를 넘길 수 없습니다.")
        @NotEmpty(message = "이메일을 작성하여 주십시오.")
        private String email;
        @Size(max = 10, message = "이름은 10글자를 넘길 수 없습니다.")
        @NotEmpty(message = "이름을 작성하여 주십시오.")
        private String myName;
        @Size(max = 20, message = "비밀번호는 30글자를 넘길 수 없습니다.")
        @NotEmpty(message = "비밀번호를 작성하여 주십시오.")
        private String password;
        @Size(max = 20, message = "전화번호는 20글자를 넘길 수 없습니다.")
        @NotEmpty(message = "전화번호를 작성하여 주십시오.")
        private String phone;
        @Size(max = 100, message = "주소는 100글자를 넘길 수 없습니다.")
        @NotEmpty(message = "주소를 작성하여 주십시오.")
        private String address;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;
        @Size(max = 15, message = "사업자 등록 번호는 12글자를 넘길 수 없습니다.")
        @NotEmpty(message = "사업자 등록 번호를 작성하여 주십시오.")
        private String businessNumber;
        @Size(max = 12, message = "회사명은 12자를 초과할 수 없습니다.")
        @NotEmpty(message = "회사명을 입력하여 주십시오.")
        private String compName;
        @Size(max = 50, message = "홈페이지 주소는 50글자를 넘길 수 없습니다.")
        private String homepage;
        private String imgFileName = "default.jpg";
        private LocalDate createdAt;

        public User toEntity(Integer role) {
            return User.builder()
                    .id(id)
                    .email(email)
                    .myName(myName)
                    .password(password)
                    .phone(phone)
                    .address(address)
                    .birth(birth)
                    .businessNumber(businessNumber)
                    .compName(compName)
                    .homepage(homepage)
                    .role(role)
                    .createdAt(createdAt)
                    .build();
        }
    }

    @Data
    public static class JobsIdDTO {
        private Integer jobsId;
    }
}