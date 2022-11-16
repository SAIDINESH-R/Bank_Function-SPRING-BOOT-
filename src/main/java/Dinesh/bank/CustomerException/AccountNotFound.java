package Dinesh.bank.CustomerException;

public class AccountNotFound extends Exception{
	public AccountNotFound()
	{
	}
	public AccountNotFound(String msg)
	{
		super(msg);
	}

}