package megalab.apis;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.dtos.Response;
import megalab.dtos.requests.NewsRequest;
import megalab.dtos.responses.CustomPage;
import megalab.dtos.responses.FullNewsResponse;
import megalab.dtos.responses.MainNewsResponse;
import megalab.dtos.responses.NewsResponse;
import megalab.services.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class NewsAPI {

    private final NewsService newsService;

    // save
    @PostMapping
    NewsResponse save(Authentication auth, @RequestBody NewsRequest newsRequest) {
        return newsService.save(auth, newsRequest);
    }

    // find all
    @GetMapping
    CustomPage<MainNewsResponse> findAll(Authentication auth,
                                         @RequestParam(required = false, defaultValue = "false") boolean likedOnly,
                                         @RequestParam(required = false, defaultValue = "1") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size) {
        return newsService.findAll(auth, likedOnly, PageRequest.of(page, size));
    }

    // find by id
    @GetMapping("/{newsId}")
    @PermitAll
    FullNewsResponse findById(@PathVariable Long newsId) {
        return newsService.findById(newsId);
    }

    // delete
    @DeleteMapping("/{newsId}")
    Response deleteById(@PathVariable Long newsId) {
        return newsService.deleteById(newsId);
    }

    // like
    @PutMapping("/{newsId}/like")
    Response like(Authentication auth, @PathVariable Long newsId) {
        return newsService.like(auth, newsId);
    }

    // dislike
    @PutMapping("/{newsId}/dislike")
    Response disLike(Authentication auth, @PathVariable Long newsId) {
        return newsService.disLike(auth, newsId);
    }
}
