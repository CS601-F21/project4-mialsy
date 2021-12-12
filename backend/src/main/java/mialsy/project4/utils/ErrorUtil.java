package mialsy.project4.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * The utils for generating exceptions.
 *
 * @author Chuxi Wang
 */
public class ErrorUtil {
    /**
     * Gets object not found exception.
     *
     * @param className the class name
     * @param id        the id
     * @return the object not found exception
     */
    public static ResponseStatusException getObjectNotFoundException(String className, Long id) {
        String msg = "Cannot find %s with id = %d".formatted(className, id);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    /**
     * Gets object not found exception.
     *
     * @param className  the class name
     * @param fieldName  the field name
     * @param fieldValue the field value
     * @return the object not found exception
     */
    public static ResponseStatusException getObjectNotFoundException(String className, String fieldName, String fieldValue) {
        String msg = "Cannot find %s with %s = %s".formatted(className, fieldName, fieldValue);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    /**
     * Gets not authorized exception.
     *
     * @return the not authorized exception
     */
    public static ResponseStatusException getNotAuthorizedException() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Gets unprocessable entity exception.
     *
     * @return the unprocessable entity exception
     */
    public static ResponseStatusException getUnprocessableEntityException() {
        return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
