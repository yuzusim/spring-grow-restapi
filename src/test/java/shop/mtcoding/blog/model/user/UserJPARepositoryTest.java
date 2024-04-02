package shop.mtcoding.blog.model.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;

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
