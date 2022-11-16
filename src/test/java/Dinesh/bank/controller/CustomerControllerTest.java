package Dinesh.bank.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
import Dinesh.bank.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CustomerService customerservice;
    
    String exampleString = "{\"name\" : \"Dinesh\", \"email\" : \"Dinesh@gmail.com\"}";
	@Test
	public void testAddCustomer() throws Exception
	{
		Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		Customer customer = new Customer("Dinesh", "Dinesh@gmail.com", list);
		when(customerservice.addCustomers(any(Customer.class))).thenReturn(customer);
		RequestBuilder request  = MockMvcRequestBuilders.post("/customer").accept(MediaType.APPLICATION_JSON).content(exampleString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(201, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetCustomers() throws Exception
	{
		List<Customer> cList = new ArrayList<Customer>();
		Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		Customer customer = new Customer("Dinesh", "Dinesh@gmail.com", list);
		cList.add(customer);
		when(customerservice.listCustomers()).thenReturn(cList);
		RequestBuilder request  = MockMvcRequestBuilders.get("/customer").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		
	}
	
	@Test
	public void testFindCustomer() throws Exception
	{
		Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		Customer customer = new Customer("Dinesh", "Dinesh@gmail.com", list);
		customer.setCustomerId(5);
		when(customerservice.findByCustomId(customer.getCustomerId())).thenReturn(customer);
		RequestBuilder request  = MockMvcRequestBuilders.get("/customer/5").param("customerId", "5");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testDeleteCustomer() throws Exception
	{
		Customer customer  = new Customer();
		customer.setCustomerId(1);
		when(customerservice.removeCustomer(customer.getCustomerId())).thenReturn("Customer data deleted successfully!");
		RequestBuilder request  = MockMvcRequestBuilders.delete("/customer/1").param("customerId", "1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		
	}
	
	@Test
	public void testGetCustomerByName() throws Exception
	{
		Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		Customer customer = new Customer("Dinesh", "Dinesh@gmail.com", list);
		when(customerservice.findByCustomerName(customer.getName())).thenReturn(customer);
		RequestBuilder request  = MockMvcRequestBuilders.get("/customer/name/Dinesh").param("name", "Dinesh");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

}
