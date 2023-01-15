package megalab.repositories;

import megalab.dtos.responses.NewsResponse;
import megalab.models.News;
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
            order by n.date
            """)
    List<NewsResponse> findAllByUserNickname(String name);
}
