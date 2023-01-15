package megalab.apis;

import lombok.RequiredArgsConstructor;
import megalab.dtos.Response;
import megalab.dtos.responses.FileUploadResponse;
import megalab.services.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileAPI {

    private final FileService fileService;

    // upload file
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    FileUploadResponse uploadFile(@RequestBody MultipartFile file) {
        return fileService.uploadFile(file);
    }

    // delete file
    @DeleteMapping("/delete")
    Response deleteFile(@RequestParam String path) {
        return fileService.deleteFile(path);
    }
}
