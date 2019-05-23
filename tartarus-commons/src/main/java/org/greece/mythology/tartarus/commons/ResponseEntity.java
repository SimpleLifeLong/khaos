package org.greece.mythology.tartarus.commons;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseEntity<T> {

    private final Object statusCode;

    private final String statusDescription;

    private final long timestamp;

    private final T body;

    public static <T> ResponseEntity ok(T body) {
        return of(ResponseStatus.OK, body);
    }

    public static ResponseEntity serverError() {
        return of(ResponseStatus.INTERNAL_SERVER_ERROR, null);
    }

    public static ResponseEntity serverError(String statusDescription) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR.value(), statusDescription, null);
    }

    public static ResponseEntity serverError(Object statusCode, String statusDescription) {
        return of(statusCode, statusDescription, null);
    }

    public static <T> ResponseEntity of(ResponseStatus status, T body) {
        return new ResponseEntity(status.value(), status.getDescription(), System.currentTimeMillis(), body);
    }

    public static <T> ResponseEntity of(Object statusCode, String statusDescription, T body) {
        return new ResponseEntity(statusCode, statusDescription, System.currentTimeMillis(), body);
    }


}
