package shop.mtcoding.blog.domain.apply;


import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.resume.Resume;

import java.sql.Timestamp;
import java.util.Date;

public class ApplyRequest {

    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO {
        private Integer resumeId;
        private Integer userId;
        private Timestamp createdAt;
        private String area;
        private String career;
        private String edu;
        private String introduce;
        private String portLink;
        private String title;
        private String isPass;
        private Integer jobsId;
    }

    @Data
    public static class SaveDTO {
        private Integer resumeId;
        private Integer jobsId;
        private String isPass;

        public Apply toEntity(Resume resume, Jobs jobs, String isPass) {
            return Apply.builder()
                    .resume(resume)
                    .jobs(jobs)
                    .isPass(isPass)
                    .build();
        }
    }


    @AllArgsConstructor
    @Data
    public static class JobsIdAndResumeIdDTO {
        private String isPass;
    }

    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO2 {
        private Date deadLine;
        private Integer id;
        private Integer userId;
        private Timestamp createdAt;
        private String area;
        private String career;
        private String content;
        private String edu;
        private String task;
        private String title;
        private Integer resumeId;
        private Integer status;
    }
}