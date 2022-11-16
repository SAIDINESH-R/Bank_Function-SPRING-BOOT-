package Dinesh.bank.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.Customer;
import Dinesh.bank.domain.FundTransfer;
import Dinesh.bank.service.CustomerService;
import Dinesh.bank.service.TransferService;
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CustomerService customerservice;
	
	@MockBean
	TransferService transferservice;
	
	
	String exampleString = "{\"fromAc\" : 101, \"toAc\" : 104, \"amount\" : 5000}";

	@Test
	public void testGetCustomer() throws Exception
	{
		List<Account> list = new ArrayList<Account>();
		Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
		list.add(ac);
		when(customerservice.findByAccountType(ac.getAccType())).thenReturn(list);
		RequestBuilder request  = MockMvcRequestBuilders.get("/account/type/Savings").param("accType", "Savings");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetAccNoDetails() throws Exception
	{
		Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
		when(customerservice.findByAccountNo(ac.getAccNo())).thenReturn(ac);
		RequestBuilder request  = MockMvcRequestBuilders.get("/account/number/101").param("accNo", "101");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}
	
	@Test
	public void testTransferFund() throws Exception
	{
		when(transferservice.amountTransfer(any(FundTransfer.class))).thenReturn("Amount transferred successfully!");
		RequestBuilder request  = MockMvcRequestBuilders.post("/account/transfer").content(exampleString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	
}
