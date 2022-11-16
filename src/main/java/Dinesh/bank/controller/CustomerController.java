package Dinesh.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import Dinesh.bank.domain.Customer;
import Dinesh.bank.CustomerException.CustomerNotFound;
import Dinesh.bank.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
	{
		Customer add = customerservice.addCustomers(customer);
		return new ResponseEntity<Customer>(add, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() throws CustomerNotFound
	{
		List<Customer> custList = customerservice.listCustomers();
		if(custList.size()==0)
			throw new CustomerNotFound("Customer Not Found.");
		return new ResponseEntity<List<Customer>>(custList, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateData(@PathVariable int id, @RequestBody Customer customer) throws CustomerNotFound
	{
		Customer c = customerservice.findByCustomId(id);
		if(c==null) {
			throw new CustomerNotFound("Customer Not Found.");
		}
		else {
		Customer cs = customerservice.updateCustomer(id, customer);
		return new ResponseEntity<Customer>(cs, HttpStatus.OK);
	}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findCustomer(@PathVariable int id) throws CustomerNotFound
	{
		Customer c = customerservice.findByCustomId(id);
		if(c==null) 
			throw new CustomerNotFound("Customer Not Found.");
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id)
	{
		return new ResponseEntity<String>(customerservice.removeCustomer(id),HttpStatus.OK);
		
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) throws CustomerNotFound
	{
		Customer c = customerservice.findByCustomerName(name);
		if(c==null)
			throw new CustomerNotFound("Customer Not Found");
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
	}
	

}