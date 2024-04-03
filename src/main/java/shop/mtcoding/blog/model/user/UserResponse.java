package shop.mtcoding.blog.model.user;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.skill.Skill;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    //유저 회원가입 DTO
    @Data
    public static class UserJoinDTO {
        private Integer id;
        private String email;
        private String myName;
        private String phone;
        private String address;
        private LocalDate birth;

        @Builder
        public UserJoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.myName = user.getMyName();
            this.phone = user.getPhone();
            this.address = user.getAddress();
            this.birth = user.getBirth();
        }
    }


    @Data
    public static class JobsKeywordDTO{

    }

    // 개인 회원정보 수정 DTO
    @Data
    public static class UserUpdateDTO{
        private String myName;
        private String phone;
        private LocalDate birth;
        private String address;

        @Builder
        public UserUpdateDTO(User user) {
            this.myName = user.getMyName();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.address = user.getAddress();
        }
    }

    @Data
    public static class UserUpdateFormDTO{
        private String myName;
        private String phone;
        private LocalDate birth;
        private String address;

        @Builder
        public UserUpdateFormDTO(User user) {
            this.myName = user.getMyName();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.address = user.getAddress();
        }
    }

    @Data
    public static class UserHomeDTO{
        // count
        private Integer applyCount;
        private Integer waitCount;
        private Integer resultCount;
        //resumeList
        private List<ResumeDTO> resumeList;
        //skillList

        @Builder
        public UserHomeDTO(Integer applyCount, Integer waitCount, Integer resultCount, List<Resume> resumeList) {
            this.applyCount = applyCount;
            this.waitCount = waitCount;
            this.resultCount = resultCount;
            this.resumeList = resumeList.stream().map(ResumeDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public class ResumeDTO {
            private Integer id;
            private String title;
            private String edu;
            private String career;
            private String area;
            private List<SkillDTO> skillList;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
                this.edu = resume.getEdu();
                this.career = resume.getCareer();
                this.area = resume.getArea();
                this.skillList = resume.getSkillList().stream().map(skill -> {
                    return new SkillDTO(skill);
                }).collect(Collectors.toList());;
            }
        }
        @Data
        public class SkillDTO{
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
        }
    }




    @Data
    public static class UserResumeSkillV2DTO{
        private Integer id;
        private String myName;
        private String address;
        private Integer resumeId;
        private String title; // resume
        private String career; // resume
        private List<Skill2DTO> skillList;

        @Builder
        public UserResumeSkillV2DTO(User user, Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.address = user.getAddress();
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.skillList = skillList.stream()
                    .map(skill -> new Skill2DTO(skill))
             .collect(Collectors.toList());
        }
    }

    @Data
    public static class SkillV2DTO{
        private Integer id;
        private String name;
        private String color;

        public SkillV2DTO(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();
            this.color = skill.getColor();
        }
    }


    @Data
    public static class UrsDTO {
        //user
        private Integer id;
        private String compName;
        //jobs
        private Integer jobsId;
        private String jobsTitle;
        private String jobsCareer;
        //Apply
        private String isPass;
        //skill
        private Integer resumeId;
        private List<SkillDTO> skillList;

        @Builder
        public UrsDTO(User user, Jobs jobs, Apply apply,Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.compName = user.getCompName();
            this.jobsId = jobs.getId();
            this.jobsTitle = jobs.getTitle();
            this.jobsCareer = jobs.getCareer();
            this.isPass = apply.getIsPass();
            this.resumeId = resume.getId();
            this.skillList = skillList.stream()
                    .map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }

    }

    @Data
    public static class Skill2DTO{
        private Integer id;
        private String name;
        private String color;

        public Skill2DTO(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();
            this.color = skill.getColor();
        }
    }

    @Data
    public static class UserResumeSkillDTO {
        private Integer id;
        private String myName;
        private String career;
        private String resumeTitle;
        private Integer resumeId;
        private List<SkillDTO> skillList;

        @Builder
        public UserResumeSkillDTO(User user, Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.career = resume.getCareer();
            this.resumeTitle = resume.getTitle();
            this.resumeId = resume.getId();
            this.skillList = skillList.stream()
                    .map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class SkillDTO {
        private Integer id;
        private String name;
        private String color;

        public SkillDTO(Skill skill) {
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

}
