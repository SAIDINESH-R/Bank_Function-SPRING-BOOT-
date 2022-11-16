package Dinesh.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Dinesh.bank.domain.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
         public List<Account> findByAccType(String type);
         public Account findByAccNo(long accNo);
}
