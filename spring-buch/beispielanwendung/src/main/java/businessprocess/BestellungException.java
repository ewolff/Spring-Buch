package businessprocess;

public class BestellungException extends Exception {

    private static final long serialVersionUID = 4356005314076037376L;
    public BestellungException() {
        super();
    }
    public BestellungException(String message) {
        super(message);
    }
    public BestellungException(String message, Throwable cause) {
        super(message, cause);
    }
    public BestellungException(Throwable cause) {
        super(cause);
    }
}
