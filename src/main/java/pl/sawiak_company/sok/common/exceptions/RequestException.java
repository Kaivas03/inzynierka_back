package pl.sawiak_company.sok.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequestException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
    private final String errorCode;

    public RequestException(String message) {
        this.message = message;
        this.httpStatus = null;
        this.errorCode = "";
    }

    public RequestException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCode = "";
    }
}
