package megalab.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    private Long id;
    private String image;
    private LocalDate date;
    private String heading;
    private String shortDescription;
}
