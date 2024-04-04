package shop.mtcoding.blog.domain.skill;

import lombok.Data;

import java.util.List;

public class SkillResponse {

    @Data
    public static class SkillCheckedDTO {
        private boolean java = false;
        private boolean javaScript = false;
        private boolean spring = false;
        private boolean htmlCss = false;
        private boolean jquery = false;
        private boolean jsp = false;
        private boolean vueJs = false;
        private boolean oracle = false;
        private boolean mySql = false;
        private boolean react = false;

        public SkillCheckedDTO (List<Skill> skillNames){
            for (Skill skillName : skillNames){
                if(skillName.getName().equals("Java")){
                    this.java = true;
                }else if(skillName.getName().equals("JavaScript")){
                    this.javaScript = true;
                }else if(skillName.getName().equals("Spring")){
                    this.spring = true;
                }else if(skillName.getName().equals("HTML/CSS")){
                    this.htmlCss = true;
                }else if(skillName.getName().equals("Jquery")){
                    this.jquery = true;
                }else if(skillName.getName().equals("JSP")){
                    this.jsp = true;
                } else if(skillName.getName().equals("Vue.js")){
                    this.vueJs = true;
                }else if(skillName.getName().equals("Oracle")){
                    this.oracle = true;
                }else if(skillName.getName().equals("MySql")){
                    this.mySql = true;
                }else if(skillName.getName().equals("React")){
                    this.react = true;
                }
            }
        }
    }
}
