package fr.iocean.species.advices;

import fr.iocean.species.dto.ErrorDto;
import fr.iocean.species.dto.InvalidEntityErrorDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    public static class EntityToCreateHasAnIdException extends RuntimeException {
        public EntityToCreateHasAnIdException(String message) {
            super(message);
        }
    }

    public static class EntityToUpdateHasAnIdException extends RuntimeException {
        public EntityToUpdateHasAnIdException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        return new ErrorDto(new Date(), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({EntityToCreateHasAnIdException.class, EntityToUpdateHasAnIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequestException(RuntimeException ex, HttpServletRequest request) {
        return new ErrorDto(new Date(), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidEntityErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> globalErrors = ex.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errorMessages = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return new InvalidEntityErrorDto(
                new Date(),
                "La vérification à échouée",
                request.getRequestURI(),
                globalErrors,
                errorMessages
        );
    }
}

