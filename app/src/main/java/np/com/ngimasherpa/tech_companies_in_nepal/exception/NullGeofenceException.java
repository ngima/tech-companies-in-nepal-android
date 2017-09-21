package np.com.ngimasherpa.tech_companies_in_nepal.exception;

/**
 * Created by ngima on 7/21/17.
 */

public class NullGeofenceException extends Exception {


    private static final String DEFAULT_MESSAGE = "Geofence is null";

    public NullGeofenceException() {
        super(DEFAULT_MESSAGE);
    }

    public NullGeofenceException(final String message) {
        super(message == null || message.isEmpty() ? DEFAULT_MESSAGE : message);
    }

    public NullGeofenceException(final String message, final Throwable cause) {
        super(message == null || message.isEmpty() ? DEFAULT_MESSAGE : message, cause);
    }

    public NullGeofenceException(final Throwable cause) {
        super(cause);
    }
}
