package kodlama.io.rentacar.core.configuration.exceptions;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.core.results.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//controllerdan gelebilecek her hatayı dinliyor
@RestControllerAdvice //hatayı dinliyor ve buraya getiriyor
public class RestExceptionHandler {

    @ExceptionHandler// gelen hatayı yönetiyor
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //422
    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<>(BusinessException.class, exception.getMessage());
    }
    @ExceptionHandler// gelen hatayı yönetiyor
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ExceptionResult<RuntimeException> handleRuntimeException(RuntimeException exception) {
        return new ExceptionResult<>(RuntimeException.class, exception.getMessage());
    }

}
