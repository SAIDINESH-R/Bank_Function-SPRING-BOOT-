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
import Dinesh.bank.dao.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	
    @Autowired
    AccountRepository accountrepo;
    
    Account ac = new Account(101,"icici", "Savings", 15000, new Customer());
    
	@Test
	public void testFindByAccType()
	{
		accountrepo.save(ac);
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		List<Account> account = accountrepo.findByAccType("Savings");
		assertEquals(1, account.size());
		
	}
	
	@Test
	public void testFindByAccNo()
	{
		accountrepo.save(ac);
		Assert.assertNotNull(accountrepo.findByAccNo(101));
		//assertEquals(ac, account);
		
	}

}
