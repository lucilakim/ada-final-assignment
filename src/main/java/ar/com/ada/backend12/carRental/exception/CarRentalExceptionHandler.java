package ar.com.ada.backend12.carRental.exception;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CarRentalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ApiReturnable> handleNotFoundException(NotFoundException exception, WebRequest request) {
        logger.warn(exception.getMessage(), exception);
        return new ResponseEntity<>(new ApiMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiReturnable> handleBadRequestException(BadRequestException exception, WebRequest request) {
        logger.warn(exception.getMessage(), exception);
        return new ResponseEntity<>(new ApiMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ApiReturnable> handleUnauthorizedException(UnauthorizedException exception, WebRequest request) {
        logger.warn(exception.getMessage(), exception);
        return new ResponseEntity<>(new ApiMessage(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleAllException(RuntimeException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ApiMessage("There was an unexpected error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
