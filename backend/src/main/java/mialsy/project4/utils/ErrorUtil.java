package mialsy.project4.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorUtil {
    public static ResponseStatusException getObjectNotFoundException(String className, Long id) {
        String msg = "Cannot find %s with id = %d".formatted(className, id);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    public static ResponseStatusException getNotAuthorizedException() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    public static ResponseStatusException getUnprocessableEntityException() {
        return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
