package kodlama.io.rentacar.core.results;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResult<T extends Exception> {
    private LocalDateTime timestamp;
    private String type;
    private String message;

    public ExceptionResult(Class<T> type, String message) {
        this.timestamp = LocalDateTime.now();
        this.type = convertToUpperCaseWithUnderscores(type.getSimpleName());
        this.message = message;
    }
    private String convertToUpperCaseWithUnderscores(String camelCaseString) {
        return camelCaseString.replaceAll("(.)(\\p{Upper})", "$1_$2").toUpperCase();
    }
}
