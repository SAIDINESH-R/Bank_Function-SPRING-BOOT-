package Dinesh.bank.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.Customer;
import Dinesh.bank.dao.AccountRepository;
import Dinesh.bank.dao.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	
	@InjectMocks
	CustomerService customerservice;
	
	@Mock
	CustomerRepository customerrepo;
	
	@Mock
	AccountRepository accountrepo;
	
	Customer customer = new Customer("Dinesh", "Dinesh@gmail.com", new ArrayList<Account>());
	Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
	
	@Test
	public void testAddCustomers() 
	{
	     customerservice.addCustomers(customer);
	     verify(customerrepo, times(1)).save(customer);
	}
	
	@Test
	public void testListCustomers()
	{
		List<Customer> list = new ArrayList<Customer>();
		list.add(customer);
		when(customerrepo.findAll()).thenReturn(list);
		List<Customer> c = customerservice.listCustomers();
		assertEquals(1, c.size());
	}
	
	
	@Test
	public void testFindByCustomId()
	{
		customer.setCustomerId(1);
		when(customerrepo.findByCustomerId(1)).thenReturn(customer);
		Customer c = customerservice.findByCustomId(1);
		assertEquals(1, c.getCustomerId());
		assertEquals("Dinesh", c.getName());
		
	}
	
	
	@Test
	public void testFindByAccountType()
	{
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		when(accountrepo.findByAccType("Savings")).thenReturn(list);
		List<Account> a = customerservice.findByAccountType("Savings");
		assertEquals(1, a.size());
	}
	
	@Test
	public void testFindByAccountNo()
	{
		when(accountrepo.findByAccNo(101)).thenReturn(ac);
		Account a = customerservice.findByAccountNo(101);
		assertEquals("icici", a.getBankName());
		assertEquals("Savings", a.getAccType());
	}
	
	@Test
	public void testRemoveCustomer()
	{
		when(customerrepo.findByCustomerId(1)).thenReturn(customer);
	    customerservice.removeCustomer(1);
		verify(customerrepo, times(1)).deleteById(1);
	}
	
	@Test
	public void testFindByCustomerName()
	{
		when(customerrepo.findByName("Dinesh")).thenReturn(customer);
		Customer c = customerservice.findByCustomerName("Dinesh");
		assertEquals("Dinesh", c.getName());
		assertEquals("Dinesh@gmail.com", c.getEmail());
	}

}
