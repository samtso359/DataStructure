package linear;

public class QueueFullException extends Exception{	//exceptions are objects extends makes it connect to exceptions

	public QueueFullException() {// empty constructor 
		super(); // invoking exception's class no-arg constructor
	}
	public QueueFullException(String msg) { // constructor with one parameter
		super(msg);	// invoking exception's class one arg constructor
	}
}