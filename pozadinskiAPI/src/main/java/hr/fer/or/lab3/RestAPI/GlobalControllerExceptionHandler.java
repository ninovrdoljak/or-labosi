package hr.fer.or.lab3.RestAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import hr.fer.or.lab3.RestAPI.dto.MessageResponse;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> unknownException(Exception ex, WebRequest req) {
        return new ResponseEntity<MessageResponse>(new MessageResponse("Not Implemented","Method not implemented for requested resource",null),HttpStatus.NOT_IMPLEMENTED);
    }
}
