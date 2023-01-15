package megalab.repositories;

import megalab.dtos.responses.CategoryResponse;
import megalab.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("""
            select new megalab.dtos.responses.CategoryResponse(c.id, c.name)
            from Category c order by c.name
            """)
    List<CategoryResponse> findAllAndCast();
}
