package shop.mtcoding.blog.model.jobs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @Data
    public static class JobsSaveDTO{

        @Size(min = 1, max = 10, message = "제목이 공백이거나 10자 이상입니다.")
        private String title;

        @NotEmpty(message = "근무 지역을 선택하여 주십시오.")
        private String area;

        @NotEmpty(message = "학력사항을 선택하여 주십시오.")
        private String edu;

        @NotEmpty(message = "경력사항을 선택하여 주십시오.")
        private String career;

        @Size(max = 100, message = "내용은 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String content;

        private LocalDate deadLine;

        @NotEmpty(message = "업무를 선택하여 주십시오.")
        private String task;

        @NotEmpty(message = "필요기술을 선택하여 주십시오.")
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

    @Data
    public static class UpdateDTO {
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
            private Integer role = 2;

            public SkillDTO(Integer jobsId, String name) {
                this.jobsId = jobsId;
                this.name = name;
            }

            public Skill toEntity(Jobs jobs) {
                return Skill.builder()
                        .name(name)
                        .jobs(jobs)
                        .build();
            }
        }
    }
}
