package it.nfalco79.example.api.core;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Thrown to indicate that there was an error in the business logic and can be
 * managed in some way by the caller.
 */
@SuppressWarnings("serial")
public class ExampleServiceException extends Exception {
    private final String errorCode;

    public ExampleServiceException(@Nullable String errorCode, @Nullable String message) {
        this(errorCode, message, null);
    }

    public ExampleServiceException(@Nullable String errorCode, @Nullable String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
