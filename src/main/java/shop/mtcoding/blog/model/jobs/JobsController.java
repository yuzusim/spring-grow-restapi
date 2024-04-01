package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class JobsController {
    private final JobsService jobsService;
    private final ResumeService resumeService;
    private final HttpSession session;

    @GetMapping("/jobs/jobs-detail/{jobsId}")
    public String jobsDetail(@PathVariable Integer jobsId, HttpServletRequest request){
        User sessionUser = (User)session.getAttribute("sessionUser");

        if (sessionUser == null){
            throw new Exception401("인증되지 않았습니다.");
        }

        //공고정보와 사용자정보를 가져오는 detailDTO
        JobsResponse.DetailDTO detailDTO = jobsService.DetailDTO(jobsId,sessionUser);
        System.out.println("detailDTO :"+detailDTO);

        //사용자 이력서 보유내역과 지원상태를 가져오는 ResumeApplyDTO
        ResumeResponse.ResumeStateDTO resumeApplyDTOList = resumeService.findAllResumeJoinApplyByUserIdAndJobsId(sessionUser.getId(), jobsId);
        request.setAttribute("resumeApplyDTOList", resumeApplyDTOList);
        request.setAttribute("detailDTO", detailDTO);
        return "jobs/jobs-detail";
    }

    @GetMapping("/jobs/info")
    public String jobsInfo (HttpServletRequest request,
                            @RequestParam(required = false, defaultValue = "") String area,
                            @RequestParam(required = false, defaultValue = "") String skill,
                            @RequestParam(required = false, defaultValue = "") String career) {
        List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
        request.setAttribute("listDTOS", listDTOS);

        request.setAttribute("selected", JobsResponse.searchDTO.builder()
                                                        .area(area)
                                                        .skill(skill)
                                                        .career(career)
                                                        .build());
        return "/jobs/info";
    }

    @GetMapping("/jobs/write-jobs-form")
    public String writeJobsForm(HttpServletRequest request) {
        User sessionComp = (User)session.getAttribute("sessionComp");

        // sessionComp 라고 주면 오류나서 sessionC라고 하였음
        request.setAttribute("sessionC", sessionComp);

        return "/jobs/write-jobs-form";
    }

    @PostMapping("/jobs/save")
    public String save (JobsRequest.JobWriterDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        jobsService.save(reqDTO);

        return "redirect:/comp/" + sessionComp.getId() + "/comp-home";
    }

    @PostMapping("/jobs/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        jobsService.delete(id);

        return "redirect:/comp/" + sessionComp.getId() + "/comp-home";
    }

    @PostMapping("/jobs/{id}/update")
    public String update(@PathVariable Integer id, JobsRequest.UpdateDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        jobsService.update(id, reqDTO);


        return "redirect:/comp/" + sessionComp.getId() + "/comp-home";
    }

    @GetMapping("/jobs/{jobsId}/update-jobs-form")
    public String updateForm (@PathVariable Integer jobsId, HttpServletRequest request) {
        JobsResponse.JobUpdateDTO job = jobsService.updateForm(jobsId);
        request.setAttribute("job", job);

        return "/jobs/update-jobs-form";
    }
}
