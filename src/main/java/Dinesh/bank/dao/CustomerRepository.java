package Dinesh.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Dinesh.bank.domain.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByName(String name);
    public Customer findByCustomerId(int id);
}
