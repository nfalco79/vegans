package it.nfalco79.example.core.internal.jaxrs;

import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import it.nfalco79.example.core.internal.CoreConsts;
import it.nfalco79.example.core.internal.rest.dto.ErrorMessageDTO;

abstract class AbstractExceptionMapper {

    @NonNull
    protected ErrorMessageDTO buildErrorMessage(@NonNull Exception exception) {
        return buildErrorMessage(null, exception);
    }

    @NonNull
    protected ErrorMessageDTO buildErrorMessage(@Nullable String code, @NonNull Exception exception) {
        Assert.notNull(exception, "exception is null");

        String errorCode = code;
        String errorMessage = exception.getLocalizedMessage();
        if (isBlank(errorCode)) {
            errorCode = CoreConsts.UNKNOW_ERROR;
            errorMessage = exception.getClass().getName() + ": " + errorMessage + ". See log for more details.";
        }
        return new ErrorMessageDTO(errorCode, errorMessage);
    }
}
