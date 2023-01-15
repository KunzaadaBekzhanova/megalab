package megalab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "news")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User writer;

    // this field will be path of an image
    private String newsCover;

    private String heading;

    private String shortDescription;

    // Текст новости
    private String newsContent;

    @ManyToMany(mappedBy = "newsList")
    private List<Category> categories;
}