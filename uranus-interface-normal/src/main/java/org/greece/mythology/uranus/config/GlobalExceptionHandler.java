package org.greece.mythology.uranus.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.greece.mythology.tartarus.commons.ResponseEntity;
import org.springframework.beans.PropertyAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

import static org.greece.mythology.tartarus.commons.ResponseStatus.ARGUMENT_NOT_VALID;

/**
 * 通用异常处理返回
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 数据校验异常捕捉
     *
     * @see javax.validation.Valid
     * @see org.springframework.validation.annotation.Validated
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, PropertyAccessException.class})
    public @ResponseBody
    ResponseEntity handleMethodArgumentNotValidException(Exception ex) {

        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        String message = ARGUMENT_NOT_VALID.getDescription();
        if (CollectionUtils.isNotEmpty(errors)) {
            FieldError error = (FieldError) errors.get(0);
            message = error.getDefaultMessage();
        }
        log.error("MethodArgumentNotValidException occurred : {}", ex.getMessage());
        return ResponseEntity.serverError(ARGUMENT_NOT_VALID.value(), message);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public @ResponseBody
    ResponseEntity handleConstraintViolationException(ConstraintViolationException ex) {

        log.error("ConstraintViolationException occurred: {}", ex.getMessage());
        String message = ARGUMENT_NOT_VALID.getDescription();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            ConstraintViolation<?> violation = constraintViolations.iterator().next();
            message = violation.getMessage();
        }
        return ResponseEntity.serverError(ARGUMENT_NOT_VALID.value(), message);
    }


    /**
     * Exception 处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity handler(Exception ex) {
        log.error("unknown Exception occurred:", ex);
        return ResponseEntity.serverError();
    }

}
