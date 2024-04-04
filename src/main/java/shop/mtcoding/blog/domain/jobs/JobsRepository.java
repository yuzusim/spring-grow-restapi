package shop.mtcoding.blog.domain.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JobsRepository {
    private final EntityManager em;

    public List<JobResponse.DTO> findAllWithUserV2(){
        String q = """
                select jt.id, jt.user_id, jt.area, jt.title, jt.edu, jt.career, jt.content, jt.dead_line, jt.task, jt.created_at,ut.img_file_name, ut.comp_name 
                from jobs_tb jt inner join user_tb ut 
                on jt.user_id = ut.id;
                """;

        Query query = em.createNativeQuery(q);
        // 엔티티랑 다른 모양의 쿼리를 직접 DTO로 만들어서 매핑하기 위한 툴
        JpaResultMapper mapper = new JpaResultMapper();
        // mapper.list ( 쿼리, 결과를 위한 DTO)로 만들고 결과가 여러개라면 List<DTO>로 생산후 반환
        List<JobResponse.DTO> result = mapper.list(query, JobResponse.DTO.class);
        return result;
    }

    public List<Jobs> findAllV2() {
        String q = """
                select * from jobs_tb order by id desc;
                """;

        Query query = em.createNativeQuery(q, Jobs.class);
        return query.getResultList();
    }
}
