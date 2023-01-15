package megalab.apis;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.dtos.requests.CategoryRequest;
import megalab.dtos.responses.CategoryResponse;
import megalab.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@PermitAll
public class CategoryAPI {

    private final CategoryService categoryService;

    @GetMapping
    List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PostMapping
    CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }
}
