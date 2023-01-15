package megalab.exceptions.hadler;

import megalab.dtos.responses.ExceptionResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ExceptionResponse handleException(Exception e) {
        e.printStackTrace();
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
