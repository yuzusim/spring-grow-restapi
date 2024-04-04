package shop.mtcoding.blog.domain.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import shop.mtcoding.blog.domain.comp.CompRequest;
import shop.mtcoding.blog.domain.resume.ResumeResponse;

import java.time.LocalDate;

@NoArgsConstructor
@Table(name = "user_tb")
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String myName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    private String address;
    private LocalDate birth;
    @Column(unique = true)
    private String businessNumber;
    private String photo;
    private String compName;
    private String homepage;
//    @Column(nullable = false)
    private Integer role;


    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    private String imgFileName;

    @Builder
    public User(Integer id, String email, String myName, String password, String phone, String address, LocalDate birth, String businessNumber, String photo, String compName, String homepage, Integer role, LocalDate createdAt) {
        this.id = id;
        this.email = email;
        this.myName = myName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.businessNumber = businessNumber;
        this.compName = compName;
        this.homepage = homepage;
        this.role = role;
        this.createdAt = createdAt;
        this.imgFileName = "2cfe3a66-74cb-4688-9f41-f417b1db694e_naver.jpg";
    }

    public ResumeResponse.UserDTO toDTO() {
        return ResumeResponse.UserDTO.builder()
                .id(this.id)
                .email(this.email)
                .myName(this.myName)
                .birth(this.birth)
                .address(this.address)
                .imgFileName(this.imgFileName)
                .phone(this.phone)
                .role(this.role)
                .createdAt(this.createdAt)
                .build();
    }


    // 기업 사용자 정보 업데이트 폼
    public void updateComp(UserRequest.UpdateCompDTO reqDTO) {
        this.myName = reqDTO.getMyName();
        this.password = reqDTO.getPassword();
        this.compName = reqDTO.getCompName();
        this.phone = reqDTO.getPhone();
        this.businessNumber = reqDTO.getBusinessNumber();
        this.homepage = reqDTO.getHomepage();
        this.birth = reqDTO.getBirth();
        this.address = reqDTO.getAddress();
    }

    // 개인 사용자 정보 업데이트 폼
    public void updateUser(UserRequest.UpdateUserDTO reqDTO) {
        this.myName = reqDTO.getMyName();
        this.password = reqDTO.getPassword();
        this.birth = reqDTO.getBirth();
        this.phone = reqDTO.getPhone();
        this.address = reqDTO.getAddress();
    }
}



