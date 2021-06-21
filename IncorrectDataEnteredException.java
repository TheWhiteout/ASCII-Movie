
public class IncorrectDataEnteredException extends RuntimeException {
	public IncorrectDataEnteredException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
