package shop.mtcoding.blog.model.resume;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeResponse {

    @Data // comp-manage페이지에 뿌려지는 resume용 DTO
    public static class CmrDTO {
        private Integer id;
        //resume
        private String title;
        private String edu;
        private String career;
        private String area;
        private Integer resumeId;
        //apply
        private String isPass;
        //skillList
        private List<SkillDTO2> skillList;

        @Builder
        public CmrDTO(Resume resume, Apply apply, List<Skill> skillList) {
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.area = resume.getArea();
            this.resumeId = resume.getId();
            this.isPass = apply.getIsPass();
            this.skillList = skillList.stream().map(SkillDTO2::new)
                    .collect(Collectors.toList());
        }
    }

    // 이력서 수정
    @Data
    public static class UpdateDTO {
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private SkillResponse.SkillCheckedDTO skillChecked;

        @Builder
        public UpdateDTO(String title, String area, String edu, String career, String introduce, String portLink, SkillResponse.SkillCheckedDTO skillChecked) {
            this.title = title;
            this.area = area;
            this.edu = edu;
            this.career = career;
            this.introduce = introduce;
            this.portLink = portLink;
            this.skillChecked = skillChecked;

        }
    }

    @Data
    public static class DetailDTO2 {
        private Integer id;
        private String title;
        private String edu;
        private String introduce;
        private String imgFileName;
        private String myName;
        private LocalDate birth;
        private String phone;
        private String email;
        private String address;
        private String area;
        private String career;
        private String portLink;
        private Integer userId;
        private List<SkillDTO2> skills;

        @Builder

        public DetailDTO2(Integer id, String title, String edu, String introduce, String imgFileName, String myName, LocalDate birth, String phone, String email, String address, String area, String career, String portLink, Integer userId, List<Skill> skills) {
            this.id = id;
            this.title = title;
            this.edu = edu;
            this.introduce = introduce;
            this.imgFileName = imgFileName;
            this.myName = myName;
            this.birth = birth;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.area = area;
            this.career = career;
            this.portLink = portLink;
            this.userId = userId;
            this.skills = skills.stream()
                    .map(skill -> new SkillDTO2(skill))
                    .collect(Collectors.toList());
        }
    }

    //이력서 상세보기 DTO
    @Data
    public static class DetailDTO {
        private Integer id;
        private Integer jobsId;
        private String title;
        private String edu;
        private String introduce;
        private String imgFileName;
        private String myName;
        private LocalDate birth;
        private String phone;
        private String email;
        private String address;
        private String area;
        private String career;
        private String portLink;
        private String isApply;
        private Integer userId;
        private Integer role;
        private Boolean isComp;
        private Boolean isUser;
        private Boolean isPass;
        private Boolean isFail;
        private Boolean isWaiting;
        private List<SkillDTO2> skills;

        @Builder
        public DetailDTO(Resume resume, Integer jobsId, String isApply, User user, Integer role, List<Skill> skills) {
            this.id = resume.getId();
            this.jobsId = jobsId;
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.introduce = resume.getIntroduce();
            this.imgFileName = user.getImgFileName();
            this.myName = user.getMyName();
            this.isApply = isApply;
            this.birth = user.getBirth();
            this.phone = user.getPhone();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.area = resume.getArea();
            this.career = resume.getCareer();
            this.portLink = resume.getPortLink();
            this.userId = user.getId();
            this.role = role;
            this.isComp = false;
            this.isUser = false;
            this.isPass = false;
            this.isFail = false;
            this.isWaiting = false;
            this.skills = skills.stream()
                    .map(skill -> new SkillDTO2(skill))
                    .collect(Collectors.toList());

            if (this.role == 1) {
                this.isUser = true;
            } else if (this.role == 2) {
                this.isComp = true;
            }

            if (this.isApply.equals("3")) {
                this.isPass = true;
            } else if (this.isApply.equals("4")) {
                this.isFail = true;
            } else if (this.isApply.equals("2")) {
                this.isWaiting = true;
            }
        }
    }

    @Data
    public static class SkillDTO2 {
        private Integer id;
        private String name;
        private String color;

        public SkillDTO2(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();

            // 혹시 언어 추가할게 있으면 else if랑 컬러, 같은 양식 맞춰서 추가가능
            if (this.name.equals("Jquery")) {
                this.color = "badge badge-pill bg-primary";
            } else if (this.name.equals("JavaScript")) {
                this.color = "badge badge-pill bg-secondary";
            } else if (this.name.equals("Spring")) {
                this.color = "badge badge-pill bg-success";
            } else if (this.name.equals("HTML/CSS")) {
                this.color = "badge badge-pill bg-danger";
            } else if (this.name.equals("JSP")) {
                this.color = "badge badge-pill bg-warning";
            } else if (this.name.equals("Java")) {
                this.color = "badge badge-pill bg-info";
            } else if (this.name.equals("React")) {
                this.color = "badge badge-pill bg-dark";
            } else if (this.name.equals("Vue.js")) {
                this.color = "badge badge-pill bg-Indigo";
            } else if (this.name.equals("Oracle")) {
                this.color = "badge badge-pill bg-brown";
            } else if (this.name.equals("MySql")) {
                this.color = "badge badge-pill bg-purple";
            }
            // 추가 양식
            // else if (this.name.equals("언어")){
            //      this.color = "badge 컬러 " ;

        }
    }

    @Builder
    @Data
    public static class ResumeDTO {
        private Integer id;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private UserDTO user;
        private List<SkillDTO> skillList;
        private LocalDate createdAt;
    }

    @Data
    @Builder
    public static class UserDTO {
        private Integer id;
        private String email;
        private String myName;
        private String phone;
        private String address;
        private LocalDate birth;
        private Integer role;
        private LocalDate createdAt;
        private String imgFileName;
    }

    @Data
    @Builder
    public static class SkillDTO {
        private Integer id;
        private String name;
        private Integer role;
        private String color;
    }


    @Data
    public static class ResumeStateDTO {
        private Boolean isApply;
        private List<ResumeApplyDTO> applys;

    }
    @Data
    public static class ResumeApplyDTO {
        private Integer id;
        private String title;
        private Integer userId;
        private String isPass;

        @Builder
        public ResumeApplyDTO(Resume resume, Apply apply) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.userId = resume.getUser().getId();
            this.isPass = apply.getIsPass();
        }
    }
}



