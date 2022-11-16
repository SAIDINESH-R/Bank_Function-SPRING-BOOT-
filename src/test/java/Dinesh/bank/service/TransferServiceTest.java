package Dinesh.bank.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.Customer;
import Dinesh.bank.domain.FundTransfer;
import Dinesh.bank.CustomerException.AccountNotFound;
import Dinesh.bank.dao.AccountRepository;
import Dinesh.bank.service.TransferService;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

	@InjectMocks
	TransferService transferservice;
	
	@Mock
	AccountRepository accountrepo;
	
	
	FundTransfer fundtransfer = new FundTransfer(101, 102, 1500);
	Account ac1 = new Account(101,"HDFC", "Savings", 15000, new Customer());
	Account ac2 = new Account(102,"HDFC", "Savings", 15000, new Customer());
	
	@Test
	public void testAmountTransfer() throws AccountNotFound {
		when(accountrepo.findByAccNo(fundtransfer.getFromAc())).thenReturn(ac1);
		when(accountrepo.findByAccNo(fundtransfer.getToAc())).thenReturn(ac2);
		transferservice.amountTransfer(fundtransfer);
		verify(accountrepo, times(1)).save(ac1);
		verify(accountrepo, times(1)).save(ac2);
	}

}
