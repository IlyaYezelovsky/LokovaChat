package lokova.chat;

public class ChatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8284748302445029998L;

	public ChatException() {
		super("An error occured in chat");
	}

	public ChatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ChatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ChatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
