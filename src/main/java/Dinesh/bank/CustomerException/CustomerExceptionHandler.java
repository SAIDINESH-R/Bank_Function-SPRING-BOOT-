package Dinesh.bank.CustomerException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(AccountNotFound.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody ExceptionMessage handleAccountNotFound(AccountNotFound excep, HttpServletRequest request)
	{
		ExceptionMessage response = new ExceptionMessage();
		response.setMessage(excep.getMessage());
		response.setUrl(request.getRequestURI());
		return response;
	}
	
	@ExceptionHandler(CustomerNotFound.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody ExceptionMessage handleCustomerNotFound(CustomerNotFound excep, HttpServletRequest request)
	{
		ExceptionMessage response = new ExceptionMessage();
		response.setMessage(excep.getMessage());
		response.setUrl(request.getRequestURI());
		return response;
	}
}
