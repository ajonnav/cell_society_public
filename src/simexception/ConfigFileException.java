package simexception;
/**
 * Class for exception in the configuration file
 * @author aj168
 *
 */
public class ConfigFileException extends RuntimeException{

	    // for serialization
	    private static final long serialVersionUID = 1L;

	    /**
	     * Create an exception based on an issue in our code.
	     */
	    public ConfigFileException (String message, Object ... values) {
	        super(String.format(message, values));
	    }
	    
	    /**
	     * Create an exception based on a caught exception with a different message.
	     */
	    public ConfigFileException (Throwable cause, String message, Object ... values) {
	        super(String.format(message, values), cause);
	    }

}
