package shop.mtcoding.blog.domain.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class UserRequest {


    @Data
    public static class ResumeIdDTO{
        private Integer resumeId;
    }

    @Data
    public static class EmailDTO{
        private String email;
    }

    @Data
    public static class UpdateDTO{

        @Size(max = 10, message = "이름은 10글자를 넘을 수 없습니다.")
        @NotEmpty(message = "이름을 작성하여 주십시오")
        private String myName;

        @Size(max = 20, message = "비밀번호는 20글자를 넘을 수 없습니다.")
        @NotEmpty(message = "비밀번호를 작성하여 주십시오")
        private String password;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;

        @Size(max = 20, message = "전화번호는 20글자를 넘길 수 없습니다.")
        @NotEmpty(message = "전화번호를 작성하여 주십시오.")
        private String phone;

        @Size(max = 100, message = "주소는 100글자를 넘길 수 없습니다.")
        @NotEmpty(message = "주소를 작성하여 주십시오.")
        private String address;
    }
  
    @Data
    public static class JoinDTO{

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

        private String imgFileName;

        private Integer role;

        public User toEntity (Integer role){
            return User.builder()
                    .email(email)
                    .myName(myName)
                    .password(password)
                    .phone(phone)
                    .birth(birth)
                    .address(address)
                    .role(role)
                    .build();
        }
    }

    @Data
    public static class LoginDTO{

        @Size(max = 30, message = "ID는 30글자를 넘을 수 없습니다.")
        @NotEmpty(message = "ID를 작성하여 주십시오.")
        private String email;

        @Size(max = 20, message = "비밀번호는 30글자를 넘길 수 없습니다.")
        @NotEmpty(message = "비밀를 작성하여 주십시오.")
        private String password;
    }
}
