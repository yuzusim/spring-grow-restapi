package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompController {
    private final CompService compService;
    private final HttpSession session;



//    @PostMapping("/comp/{id}/update")
//    public String update(@PathVariable Integer id, CompRequest.UpdateDTO requestDTO) {
//        User sessionComp = (User) session.getAttribute("sessionComp");
//        User user = compService.updateById(sessionComp, requestDTO);
//        session.setAttribute("sessionComp", user);
//        return "redirect:/comp/" + id + "/comp-home";
//    }



    @GetMapping("/comp/comp-index")
    public String compIndex(HttpServletRequest request) {
        List<CompResponse.ResumeUserSkillDTO> rusList = compService.findAllRusList();
        request.setAttribute("rusList", rusList);
        return "comp/comp-index";
    }

    @GetMapping("/comp/read-resume")
    public String readResume(HttpServletRequest request) {
        List<CompResponse.ResumeUserSkillDTO> rusList = compService.findAllRusList();
        request.setAttribute("rusList", rusList);
        return "/comp/read-resume";
    }

    @GetMapping("/comp/{id}/comp-home")
    public String compHome(@PathVariable Integer id, @RequestParam(required = false, defaultValue = "0") Integer jobsId, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        List<CompResponse.ComphomeDTO> comphomeDTOList = compService.findAllByUserId(sessionComp.getId());
        request.setAttribute("jobsList", comphomeDTOList);

        return "/comp/comp-home";
    }

    @GetMapping("/comp/{id}/apply")
    public String offer(@PathVariable Integer id) {

        return "/comp/apply";
    }

    @GetMapping("/comp/join-form")
    public String compJoinForm() {

        return "/comp/join-form";
    }

    @PostMapping("/comp/join")
    public String compJoin(@RequestParam(name = "role") Integer role, CompRequest.CompJoinDTO reqDTO) {
        User user = compService.join(role, reqDTO);
        session.setAttribute("sessionComp", user);
        return "redirect:/comp/read-resume";
    }

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("imgFileName", sessionUser.getImgFileName());
        return "/comp/profile-update-form";
    }


    @GetMapping("/comp/{id}/scrap")
    public String scrap(@PathVariable Integer id) {
        return "/comp/scrap";
    }

    @GetMapping("/comp/talent")
    public String talent() {
        return "/comp/talent";
    }

    @GetMapping("/comp/jobs-info")
    public String jobsInfo(HttpServletRequest request) {
        List<CompResponse.JobsSkillDTO> jobsList = compService.jobsList();
        request.setAttribute("jobsList", jobsList);

        return "/comp/jobs-info";
    }
}





