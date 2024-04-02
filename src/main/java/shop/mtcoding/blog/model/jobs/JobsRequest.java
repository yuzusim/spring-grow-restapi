package shop.mtcoding.blog.model.jobs;

import lombok.Builder;
import lombok.Data;

import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.time.LocalDate;
import java.util.List;

public class JobsRequest {

    @Data
    public static class KeywordDTO{
        private String keyword;
    }

    @Data // 공고작성 DTO 2탄
    public static class JobsSaveDTO{
        private String title;
        private String area;
        private String edu;
        private String career;
        private String content;
        private LocalDate deadLine;
        private String task;
        private List<Skill> skillList;

        public Jobs toEntity(JobsSaveDTO reqDTO, User user) {
            return Jobs.builder()
                    .title(reqDTO.title)
                    .area(reqDTO.area)
                    .edu(reqDTO.edu)
                    .career(reqDTO.career)
                    .content(reqDTO.content)
                    .deadline(reqDTO.deadLine)
                    .task(reqDTO.task)
                    .skillList(reqDTO.skillList)
                    .user(user)
                    .build();
        }
    }


    // 공고작성 DTO
    @Data
    public static class JobWriterDTO {
        private String title;
        private String area;
        private String edu;
        private String career;
        private String content;
        private LocalDate deadLine;
        private String task;
        private List<String> skill;

        public Jobs toEntity(User user) {
            return Jobs.builder()
                    .title(title)
                    .area(area)
                    .edu(edu)
                    .career(career)
                    .content(content)
                    .deadline(deadLine)
                    .task(task)
                    .user(user)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String edu;
        private String career;
        private String content;
        private String area;
        private LocalDate deadLine;
        private String task;
        private List<SkillDTO> skill;

        @Data
        public static class SkillDTO {
            private Integer jobsId;
            private String name;
            private Integer role;

            public SkillDTO(Integer jobsId, String name, Integer role) {
                this.jobsId = jobsId;
                this.name = name;
                this.role = role;
            }

            public Skill toEntity(Jobs jobs) {
                return Skill.builder()
                        .name(name)
                        .role(role)
                        .jobs(jobs)
                        .build();
            }
        }
    }
}
