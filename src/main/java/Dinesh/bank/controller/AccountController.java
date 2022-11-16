package Dinesh.bank.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.FundTransfer;
import Dinesh.bank.CustomerException.AccountNotFound;
import Dinesh.bank.service.CustomerService;
import Dinesh.bank.service.TransferService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
     CustomerService customerservice;
	@Autowired
	TransferService transferService;
	
	@GetMapping("/type/{accType}")
	public ResponseEntity<List<Account>> getCustomer(@PathVariable String accType) throws AccountNotFound
	{
		List<Account> a = customerservice.findByAccountType(accType);
		if(a.size()==0)
			throw new AccountNotFound("Account Not Found");
		return new ResponseEntity<List<Account>>(a, HttpStatus.OK);
	}
	
	@GetMapping("/number/{acNo}")
	public ResponseEntity<Account> getAccNoDetails(@PathVariable long acNo) throws AccountNotFound
	{
		Account ac = customerservice.findByAccountNo(acNo);
		if(ac==null)
			throw new AccountNotFound("Account not found.");
		return new ResponseEntity<Account>(ac, HttpStatus.OK);
	}
	
	@PutMapping("/transfer")
	public String transferFund(@RequestBody FundTransfer fundtransfer)
	{
		try {
			return transferService.amountTransfer(fundtransfer);
		} catch (AccountNotFound e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

}
