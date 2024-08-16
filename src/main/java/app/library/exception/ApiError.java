package app.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private int statusCode;
    private String message;
    private String details;

    @Override
    public String toString() {
        return "ApiError{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

