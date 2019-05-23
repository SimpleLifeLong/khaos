package org.greece.mythology.tartarus.commons;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseEntity<T> {

    private final Object statusCode;

    private final String statusDescription;

    private final T body;

    public static <T> ResponseEntity ok(T body) {
        return of(HttpStatus.OK, body);
    }

    public static ResponseEntity serverError() {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public static ResponseEntity serverError(String statusDescription) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR.value(), statusDescription, null);
    }

    public static ResponseEntity serverError(Object statusCode, String statusDescription) {
        return of(statusCode, statusDescription, null);
    }

    public static <T> ResponseEntity of(HttpStatus status, T body) {
        return new ResponseEntity(status.value(), status.getReasonPhrase(), body);
    }

    public static <T> ResponseEntity of(Object statusCode, String statusDescription, T body) {
        return new ResponseEntity(statusCode, statusDescription, body);
    }


}
