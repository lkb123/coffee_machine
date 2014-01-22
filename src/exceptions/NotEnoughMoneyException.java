package exceptions;

@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {

	public NotEnoughMoneyException(String message) {
		super(message);
	}

}
