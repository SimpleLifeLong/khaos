package org.greece.mythology.tartarus.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericException extends RuntimeException {

    private final Object statusCode;

    private final String statusDescription;

    private GenericException(Object statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public static GenericException of(Object statusCode, String statusDescription) {
        return new GenericException(statusCode, statusDescription);
    }


}
