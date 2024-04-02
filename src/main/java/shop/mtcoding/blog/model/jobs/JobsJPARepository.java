package shop.mtcoding.blog.model.jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobsJPARepository extends JpaRepository <Jobs, Integer> {

    @Query("select j, u FROM Jobs j join fetch User u on j.user.id = u.id WHERE u.id = :userId")
    List<Jobs> findAllByUserId(@Param("userId") Integer userId);

    @Query("select j from Jobs j join fetch j.user u join fetch j.skillList s where j.title like %:keyword% or u.compName like %:keyword%")
    List<Jobs> findAllKeyword(@Param("keyword") String keyword);


    @Query("SELECT COUNT(j) FROM Jobs j WHERE j.user.id = :userId")
    Integer countByUserId(@Param("userId") Integer userId);


    @Query("select j from Jobs j join fetch j.user u join fetch j.skillList s where j.user.id = :jobsUserId")
    Jobs findByIdJoinUserWithSkill(Integer jobsUserId);

}