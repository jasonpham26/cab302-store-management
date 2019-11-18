package exception;

@SuppressWarnings("serial")
public class CSVFormatException extends Exception{
	public CSVFormatException() {
		super();
	}
	public CSVFormatException(String msg) {
		super(msg);
	}
}
