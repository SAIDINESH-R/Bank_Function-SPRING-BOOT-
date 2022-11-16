package Dinesh.bank.Repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.Customer;
import Dinesh.bank.dao.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerrepo;
	
	Customer customer = new Customer("Dinesh", "Dinesh@gmail.com", new ArrayList<Account>());

//	@BeforeAll
//	public void setup()
//	{
//		customerrepo.save(customer);
//	}
	
	@Test
	public void testSave()
	{
		Customer c = customerrepo.save(customer);
		assertEquals(customer, c);
	}
	
	@Test
	public void testFindAll()
	{
		customerrepo.save(customer);
		List<Customer> list = customerrepo.findAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void testFindByCustomerId()
	{
		//customer.setCustomerId(1);
		customerrepo.save(customer);
		Customer c = customerrepo.findByCustomerId(customer.getCustomerId());
		assertEquals(customer, c);
	}
	
	@Test
	public void testDeleteById()
	{
		customerrepo.save(customer);
		customerrepo.deleteById(customer.getCustomerId());
	    Assert.assertNull(customerrepo.findByCustomerId(customer.getCustomerId()));
	}
	
	@Test
	public void testFindByCustomerName()
	{
		customerrepo.save(customer);
		Customer c = customerrepo.findByName("Dinesh");
		assertEquals(customer, c);
	}
	
}
