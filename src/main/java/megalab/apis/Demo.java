package megalab.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/demo")
public class Demo {

    @GetMapping("/test")
    public Map<String, String> testing() {
        return Map.of(
                "testing", "testing"
        );
    }
}
