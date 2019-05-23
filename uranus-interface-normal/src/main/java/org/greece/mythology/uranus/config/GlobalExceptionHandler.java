package org.greece.mythology.uranus.config;

import lombok.extern.slf4j.Slf4j;
import org.greece.mythology.tartarus.commons.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 通用异常处理返回
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Exception 处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity handler(Exception ex) {
        log.error("unknown Exception occurred: " + ex.getMessage(), ex);
        return ResponseEntity.serverError();
    }

}
