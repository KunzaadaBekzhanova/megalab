package megalab.dtos.responses;

import lombok.Builder;
import lombok.Data;
import megalab.dtos.UserDTO;

import java.util.List;

@Data
@Builder
public class GetProfileResponse {
    private UserDTO userInfo;
    private List<NewsResponse> news;
}
