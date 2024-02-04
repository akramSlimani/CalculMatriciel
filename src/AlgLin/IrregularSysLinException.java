package AlgLin;

public class IrregularSysLinException extends Exception{

	public IrregularSysLinException(String message) {
		super(message);
	}
	
	public String toString() {
		return getMessage();
	}
	
}
