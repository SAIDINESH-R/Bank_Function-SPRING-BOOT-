package Dinesh.bank.CustomerException;

public class CustomerNotFound extends Exception{
	public CustomerNotFound() {}
	
	public CustomerNotFound(String msg) {
		super(msg);
	}

}