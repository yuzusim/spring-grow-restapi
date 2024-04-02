package shop.mtcoding.blog.model.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id);

    // board에 다 담을 것
    @Query("select b from Board b join fetch b.user join fetch b.replies r where b.id = :id")
    Optional<Board> findByIdJoinUserAndReplies(@Param("id") int id);

//    @Query("select new com.example.jobala.board.BoardResponse$BoardDTO(b) from Board b order by b.id DESC")
//    List<BoardResponse.BoardDTO> findBoardAll();

}
