package videos.com.videos.Principal;

public class InvalidSeriesDataException extends RuntimeException {
    public InvalidSeriesDataException(String message) {
        super(message);
    }

    public InvalidSeriesDataException(String message, Throwable cause) {
        super(message, cause);
    }
}