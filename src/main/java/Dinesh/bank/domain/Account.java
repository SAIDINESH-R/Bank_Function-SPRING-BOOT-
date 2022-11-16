package Dinesh.bank.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Account {
	@Id
	private long accNo;
	@Column
	private String bankName;
	@Column
	private String accType;
	@Column
	private double accBalance;
	
	
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public double getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ca_fk", referencedColumnName = "customerId")
	@JsonIgnoreProperties("accounts")
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Account(long accNo, String bankName, String accType, double accBalance, Customer customer) {
		super();
		this.accNo = accNo;
		this.bankName = bankName;
		this.accType = accType;
		this.accBalance = accBalance;
		this.customer = customer;
	}
	
	public Account() {

}
}