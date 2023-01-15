package megalab.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import megalab.dtos.Response;
import megalab.dtos.responses.FileUploadResponse;
import megalab.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class FileService {

    @Value("${app.folder-path}")
    private String folderPath;

    // RECOMMEND to use AWS s3 or Google File Storage
    @SneakyThrows
    public FileUploadResponse uploadFile(MultipartFile file) {
        InputStream fileContent = file.getInputStream();
        byte[] buffer = new byte[1024];
        String filePath = folderPath + LocalDateTime.now() + file.getOriginalFilename();
        File targetFile = new File(filePath);
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(targetFile));
        int read;
        while ((read = fileContent.read(buffer)) != -1) {
            outStream.write(buffer, 0, read);
        }
        outStream.flush();
        outStream.close();
        fileContent.close();
        return FileUploadResponse.builder()
                .path(filePath)
                .build();
    }

    public Response deleteFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new BadRequestException(
                    String.format("File %s not found", path)
            );
        }
        return file.delete() ? new Response(path + " is deleted!") : new Response("Delete operation is failed.");
    }
}
