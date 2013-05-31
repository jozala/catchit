package pl.aetas.catchit.exception;

/**
 * Exception thrown when some unexpected error has appear in external library responsible for making a photo
 *
 * @author Mariusz Jozala
 */
public class InternalPhotomakingException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public InternalPhotomakingException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public InternalPhotomakingException(String msg) {
        super(msg);
    }

    public InternalPhotomakingException() {
        super();
    }

}
