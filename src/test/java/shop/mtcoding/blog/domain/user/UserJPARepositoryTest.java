package shop.mtcoding.blog.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.jobs.JobsJPARepository;

import java.util.List;


@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private JobsJPARepository jobsJPARepository;

    @Test
    public void keyword_test(){
        // given
        String keyword = "백엔드";
        // when
        List<Jobs> jobsList = jobsJPARepository.findAllKeyword(keyword);
        // then
        for (int i = 0; i < jobsList.size(); i++) {
            System.out.println(jobsList.get(i).getTitle());
        }
    }
}
