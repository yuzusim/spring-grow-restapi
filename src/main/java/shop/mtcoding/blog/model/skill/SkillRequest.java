package shop.mtcoding.blog.model.skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class SkillRequest {

    @Data
    public static class JobSkillDTO{
        private String name;
        private String color;

        public JobSkillDTO(String name, String color){
            String colorClass = "";
            if (name.equals("Jquery")){
                colorClass = "badge bg-primary";
            }
            else if(name.equals("JavaScript")){
                colorClass = "badge bg-secondary";
            }
            else if(name.equals("Spring")){
                colorClass = "badge bg-success";
            }
            else if(name.equals("HTML/CSS")){
                colorClass = "badge bg-danger";
            }
            else if(name.equals("JSP")){
                colorClass = "badge bg-warning";
            }
            else if(name.equals("Java")){
                colorClass = "badge bg-info";
            }
            else if(name.equals("React")){
                colorClass = "badge bg-dark";
            }

            this.name = name;
            this.color = colorClass;
        }
    }

    @Builder
    @Data
    public static class CompSkillDTO{
        private String name;
        private String color;
    }

    @Data
    @Builder
    public static class UserSkillDTO{
        private String name;
        private String color;
    }


    @Data
    @Builder
    public static class JobsSkillDTO{
        private String name;
        private String color;
    }


    @Data
    public static class ApplySkillDTO{
        private String name;
        private String color;

        public ApplySkillDTO(String name, String color) {
            String colorClass = "";
            if (name.equals("Jquery")){
                colorClass = "badge bg-primary";
            }
            else if(name.equals("JavaScript")){
                colorClass = "badge bg-secondary";
            }
            else if(name.equals("Spring")){
                colorClass = "badge bg-success";
            }
            else if(name.equals("HTML/CSS")){
                colorClass = "badge bg-danger";
            }
            else if(name.equals("JSP")){
                colorClass = "badge bg-warning";
            }
            else if(name.equals("Java")){
                colorClass = "badge bg-info";
            }
            else if(name.equals("React")){
                colorClass = "badge bg-dark";
            }
            this.name = name;
            this.color = colorClass;
        }
    }


    @Data
    public static class ResumeSkillDTO{
        private String name;
        private String color;

        public ResumeSkillDTO(String name, String color) {
            String colorClass = "";
            if (name.equals("Jquery")){
                colorClass = "badge bg-primary";
            }
            else if(name.equals("JavaScript")){
                colorClass = "badge bg-secondary";
            }
            else if(name.equals("Spring")){
                colorClass = "badge bg-success";
            }
            else if(name.equals("HTML/CSS")){
                colorClass = "badge bg-danger";
            }
            else if(name.equals("JSP")){
                colorClass = "badge bg-warning";
            }
            else if(name.equals("Java")){
                colorClass = "badge bg-info";
            }
            else if(name.equals("React")){
                colorClass = "badge bg-dark";
            }

            this.name = name;
            this.color = colorClass;

        }
    }
}
