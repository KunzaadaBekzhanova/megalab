package megalab.repositories;

import megalab.dtos.responses.MainNewsResponse;
import megalab.dtos.responses.NewsResponse;
import megalab.models.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, Long> {

    @Query("""
            select new megalab.dtos.responses.NewsResponse(
            n.id, n.newsCover, n.date, n.heading, n.shortDescription
            )
            from News n
            join n.writer w where w.nickname = ?1
            order by n.date desc 
            """)
    List<NewsResponse> findAllByUserNickname(String name);

    @Query("""
            select new megalab.dtos.responses.MainNewsResponse(
            n.id, n.newsCover, n.date, n.heading, n.shortDescription, (w is not null))
            from News n left join n.writer w order by n.date desc
            """)
    Page<MainNewsResponse> findAllByUserNicknameAndCast(String nickname, Pageable pageable);

    @Query("""
            select new megalab.dtos.responses.NewsResponse(
            n.id, n.newsCover, n.date, n.heading, n.shortDescription
            )
            from News n
            order by n.date desc 
            """)
    Page<MainNewsResponse> findAllAndCast(Pageable pageable);
}
