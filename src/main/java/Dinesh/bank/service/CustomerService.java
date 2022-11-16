package Dinesh.bank.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.Customer;
import Dinesh.bank.CustomerException.CustomerNotFound;
import Dinesh.bank.dao.AccountRepository;
import Dinesh.bank.dao.CustomerRepository;

@Service
public class CustomerService{
	@Autowired
	CustomerRepository customerrepo;
	@Autowired
	AccountRepository accountrepo;
	
	public Customer addCustomers(Customer customer)
	{   
		List<Account> accounts = customer.getAccounts();
		List<Account> acclist =  new ArrayList<Account>();
		for(Account account : accounts)
		{
			account.setCustomer(customer);
			acclist.add(account);
		}
		customer.setAccounts(acclist);
		return customerrepo.save(customer);
		
	}
	
	public List<Customer> listCustomers()
	{
		return customerrepo.findAll();
	}
	
	
	public Customer updateCustomer(int id, Customer customer) 
	{
		     Customer cs= new Customer(); 
			cs.setCustomerId(id);
			cs.setName(customer.getName());
			cs.setEmail(customer.getEmail());
			List<Account> accounts = customer.getAccounts();
			List<Account> acclist =  new ArrayList<Account>();
			for(Account account : accounts)
			{
				account.setCustomer(cs);
				acclist.add(account);
			}
			cs.setAccounts(acclist);
		return customerrepo.save(cs);
		
	}
	
	public Customer findByCustomId(int id)
	{
		return customerrepo.findByCustomerId(id);
	}
	
	public String removeCustomer(int id)
	{
		Customer customer = customerrepo.findByCustomerId(id);
		if(customer!=null)
		{
			customerrepo.deleteById(id);
			return "Customer data deleted successfully!";
		}
		else 
		{
		    return "Customer doesn't exist";
	    }
	}
	public List<Account> findByAccountType(String type)
	{
		return accountrepo.findByAccType(type);
	}
	
	public Account findByAccountNo(long no)
	{
		return accountrepo.findByAccNo(no);
	}
	
	public Customer findByCustomerName(String name)
	{
		return customerrepo.findByName(name);
	}

}