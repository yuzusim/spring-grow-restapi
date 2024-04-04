package shop.mtcoding.blog.domain.resume;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class ResumeJPARepositoryTest {

    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private EntityManager em;


    @Test
    public void findByIdJoinUser_test() {
        int resumeId = 1;
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId);
        System.out.println(resume);
    }

    @Test
    public void delete_test(){
        //given
        Integer boardId = 1;
        //when
        resumeJPARepository.deleteById(boardId);
        em.flush();
        //then
        List<Resume> resumes = resumeJPARepository.findAll();
        System.out.println(resumes.size());
    }


}
