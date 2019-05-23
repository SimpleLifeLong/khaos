package org.greece.mythology.tartarus.commons;

public enum ResponseStatus {

    OK(200, "OK"),
    UNAUTHORIZED(401, "Unauthorized"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    ARGUMENT_NOT_VALID(900, "Method Argument Not Valid");

    private final int value;

    private final String description;

    ResponseStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getDescription() {
        return this.description;
    }
}
