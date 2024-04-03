package shop.mtcoding.blog.model.user;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.resume.ResumeResponse;

import java.sql.Date;
import java.sql.Timestamp;
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

    //회원정보용 업데이트 의미있는 메서드
    public void update(CompRequest.UpdateDTO requestDTO) {
        this.myName = requestDTO.getMyName();
        this.password = requestDTO.getPassword();
        this.compName = requestDTO.getCompName();
        this.phone = requestDTO.getPhone();
        this.homepage = requestDTO.getHomepage();
        this.birth = requestDTO.getBirth();
        this.address = requestDTO.getAddress();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", myName='" + myName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                ", businessNumber='" + businessNumber + '\'' +
                ", photo='" + photo + '\'' +
                ", compName='" + compName + '\'' +
                ", homepage='" + homepage + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", imgFileName='" + imgFileName + '\'' +
                '}';
    }
}



