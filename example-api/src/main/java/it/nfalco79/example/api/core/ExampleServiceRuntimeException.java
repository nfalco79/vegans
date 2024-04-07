package it.nfalco79.example.api.core;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Thrown to indicate that a method has been passed an inappropriate argument
 * that cause an error in the business logic.
 */
@SuppressWarnings("serial")
public class ExampleServiceRuntimeException extends RuntimeException {
    private final String errorCode;

    public ExampleServiceRuntimeException(@Nullable String errorCode, @Nullable String message) {
        this(errorCode, message, null);
    }

    public ExampleServiceRuntimeException(@Nullable String errorCode, @Nullable String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
