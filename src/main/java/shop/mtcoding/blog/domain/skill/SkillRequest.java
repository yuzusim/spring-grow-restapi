package shop.mtcoding.blog.domain.skill;

import lombok.Builder;
import lombok.Data;

public class SkillRequest {

    @Data
    public static class JobSkillDTO{
        private String name;
        private String color;

        public JobSkillDTO(String name){
            this.name = name;
        }
    }

    @Builder
    @Data
    public static class CompSkillDTO{
        private String name;
        private String color;
    }

    @Data
    public static class ApplySkillDTO{
        private String name;
        private String color;

        public ApplySkillDTO(String name) {
            this.name = name;
        }
    }
}
