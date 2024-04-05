package shop.mtcoding.blog.domain.resume;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeResponse {


    @Data  // comp-resume-detail 페이지 요청
    public static class CompResumeDetailDTO {
        private Integer resumeId;
        private String imgFileName;
        private String myName;
        private LocalDate birth;
        private String phone;
        private Integer jobsId;
        private String email;
        private String address;
        private String area;
        private String title;
        private String edu;
        private String introduce;
        private String career;
        private String portLink;
        private List<SkillDTO> skills;
        private Boolean isComp = true;
        private Boolean isPass = false;
        private Boolean isFail = false;
        private Boolean isWaiting = false;

        @Builder
        public CompResumeDetailDTO(Resume resume, User user, Apply apply, List<Skill> skills) {
            this.resumeId = resume.getId();
            this.imgFileName = user.getImgFileName();
            this.myName = user.getMyName();
            this.birth = user.getBirth();
            this.phone = user.getPhone();
            this.jobsId = user.getId();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.area = resume.getArea();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.introduce = resume.getIntroduce();
            this.career = resume.getCareer();
            this.portLink = resume.getPortLink();
            this.skills = skills.stream().map(SkillDTO::new).toList();

            if (apply.getIsPass().equals("3")) {
                this.isPass = true;
            } else if (apply.getIsPass().equals("4")) {
                this.isFail = true;
            } else if (apply.getIsPass().equals("2")) {
                this.isWaiting = true;
            }
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
        }
    }


    @Data
    public static class SaveDTO {
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private List<Skill> skillList;

        public SaveDTO(Resume resume) {
            this.title = resume.getTitle();
            this.area = resume.getArea();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.introduce = resume.getIntroduce();
            this.portLink = resume.getPortLink();
            this.skillList = resume.getSkillList();
        }
    }

    @Data // comp-manage페이지에 뿌려지는 resume용 DTO
    public static class CompManageDTO {
        private Integer id;
        private String title;
        private String edu;
        private String career;
        private String area;
        private Integer resumeId;
        private String isPass;
        private Integer jobsId;
        private List<SkillDTO> skillList;

        @Builder
        public CompManageDTO(Resume resume, Apply apply, Jobs jobs, List<Skill> skillList) {
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.area = resume.getArea();
            this.resumeId = resume.getId();
            this.isPass = apply.getIsPass();
            this.jobsId = jobs.getId();
            this.skillList = skillList.stream().map(SkillDTO::new).toList();
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
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
        private List<UpdateSkill> skillList;

        @Builder
        public UpdateDTO(Resume resume, List<Skill> skills) {
            this.title = resume.getTitle();
            this.area = resume.getArea();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.introduce = resume.getIntroduce();
            this.portLink = resume.getPortLink();
            this.skillList = skills.stream()
                    .map(updateSkill -> new UpdateSkill(updateSkill))
                    .collect(Collectors.toList());
        }

        @Data
        public static class UpdateSkill {
            private String name;

            public UpdateSkill(Skill skill) {
                this.name = skill.getName();
            }
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
        private List<SkillDTO> skills;

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
                    .map(SkillDTO::new).toList();
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
        }
    }

    @Data
    public static class CompDetailDTO2 {
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
        private List<SkillDTO> skills;

        @Builder
        public CompDetailDTO2(Integer id, String title, String edu, String introduce, String imgFileName, String myName, LocalDate birth, String phone, String email, String address, String area, String career, String portLink, Integer userId, List<Skill> skills) {
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
                    .map(skill -> new SkillDTO(skill)).toList();
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
        }
    }

    @Data
    public static class DetailDTO {
        private Integer resumeId;
        private String imgFileName;
        private String myName;
        private LocalDate birth;
        private String phone;
        private String email;
        private String address;
        private String area;
        private String title;
        private String edu;
        private String introduce;
        private String career;
        private String portLink;
        private List<SkillDTO> skills;
        private Boolean isOwner = false;

        @Builder
        public DetailDTO(Resume resume, User user, List<Skill> skills, Boolean isOwner) {
            this.resumeId = resume.getId();
            this.area = resume.getArea();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.introduce = resume.getIntroduce();
            this.career = resume.getCareer();
            this.portLink = resume.getPortLink();
            this.imgFileName = user.getImgFileName();
            this.myName = user.getMyName();
            this.birth = user.getBirth();
            this.phone = user.getPhone();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.isOwner = isOwner;
            this.skills = skills.stream().map(SkillDTO::new).toList();
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
        }
    }

    //이력서 상세보기 DTO2
    @Data
    public static class DetailDTO3 {
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
        private List<SkillDTO> skills;

        @Builder
        public DetailDTO3(Resume resume, Jobs jobs, String isApply, User user, Integer role, List<Skill> skills) {
            this.id = resume.getId();
            this.jobsId = jobs.getId();
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
                    .map(SkillDTO::new)
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

        @Data
        public class SkillDTO {
            private Integer id;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
            }
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

    @Data
    public static class UpdatedDTO {
        private Integer id;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private List<SkillDTO> skill;

        public UpdatedDTO(Resume resume) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.area = resume.getArea();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.introduce = resume.getIntroduce();
            this.portLink = resume.getPortLink();
        }
    }
}



