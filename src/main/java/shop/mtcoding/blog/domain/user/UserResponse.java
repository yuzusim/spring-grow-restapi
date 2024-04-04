package shop.mtcoding.blog.domain.user;


import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.skill.Skill;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
public class UserResponse {


    @Data  //유저 회원가입 DTO
    public static class JoinDTO {
        private Integer id;
        private String email;
        private String myName;
        private String phone;
        private String address;
        private LocalDate birth;

        @Builder
        public JoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.myName = user.getMyName();
            this.phone = user.getPhone();
            this.address = user.getAddress();
            this.birth = user.getBirth();
        }
    }


    @Data  // 개인 회원정보 수정 DTO
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

    @Data  //개인 정보수정 페이지
    public static class UpdateUserFormDTO{
        private String myName;
        private String phone;
        private LocalDate birth;
        private String address;

        @Builder
        public UpdateUserFormDTO(User user) {
            this.myName = user.getMyName();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.address = user.getAddress();
        }
    }

    @Data  // 개인 홈 페이지
    public static class HomeDTO{
        private Integer applyCount;
        private Integer waitCount;
        private Integer resultCount;
        private List<ResumeDTO> resumeList;

        @Builder
        public HomeDTO(Integer applyCount, Integer waitCount, Integer resultCount, List<Resume> resumeList) {
            this.applyCount = applyCount;
            this.waitCount = waitCount;
            this.resultCount = resultCount;
            this.resumeList = resumeList.stream().map(ResumeDTO::new).toList();
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
                this.skillList = resume.getSkillList().stream().map(SkillDTO::new).toList();
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
    }

    @Data  // ajax 이력서로 지원한 공고 내역 요청
    public static class FindJobsResumeDTO{
        private Integer id;
        private String compName;
        private Integer jobsId;
        private String jobsTitle;
        private String jobsCareer;
        private String isPass;
        private Integer resumeId;
        private List<SkillDTO> skillList;

        @Builder
        public FindJobsResumeDTO(User user, Jobs jobs, Apply apply,Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.compName = user.getCompName();
            this.jobsId = jobs.getId();
            this.jobsTitle = jobs.getTitle();
            this.jobsCareer = jobs.getCareer();
            this.isPass = apply.getIsPass();
            this.resumeId = resume.getId();
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

    @Data  // 개인 지원내역 페이지
    public static class resumeHomeDTO {
        private Integer id;
        private String myName;
        private String career;
        private String resumeTitle;
        private Integer resumeId;
        private List<SkillDTO> skillList;

        @Builder
        public resumeHomeDTO(User user, Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.career = resume.getCareer();
            this.resumeTitle = resume.getTitle();
            this.resumeId = resume.getId();
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



    @Data // 수정 완료된 기업 정보 DTO
    public static class UpdatedCompDTO {
        private String myName;
        private String password;
        private String phone;
        private String address;
        private LocalDate birth;
        private String businessNumber;
        private String compName;
        private String homepage;

        @Builder
        public UpdatedCompDTO(User user) {
            this.myName = user.getMyName();
            this.password = user.getPassword();
            this.phone = user.getPhone();
            this.address = user.getAddress();
            this.birth = user.getBirth();
            this.businessNumber = user.getBusinessNumber();
            this.compName = user.getCompName();
            this.homepage = user.getHomepage();
        }
    }

    @Data  // 기업 정보 수정 DTO
    public static class UpdateCompFormDTO {
        private Integer id;
        private String myName;
        private String compName;
        private String phone;
        private String businessNumber;
        private String homepage;
        private String address;

        public UpdateCompFormDTO(User user) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.compName = user.getCompName();
            this.phone = user.getPhone();
            this.businessNumber = user.getBusinessNumber();
            this.homepage = user.getHomepage();
            this.address = user.getAddress();
        }
    }
}
