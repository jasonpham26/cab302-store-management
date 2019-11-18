package exception;

@SuppressWarnings("serial")
public class DeliveryException extends Exception{
	public DeliveryException() {
		super();
	}
	
	public DeliveryException(String msg) {
		super(msg);
	}
}
