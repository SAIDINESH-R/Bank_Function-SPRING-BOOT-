package Dinesh.bank.domain;

import org.springframework.stereotype.Component;

@Component
public class FundTransfer {
	private long fromAc;
	private long toAc;
	private double amount;
	public long getFromAc() {
		return fromAc;
	}
	public void setFromAc(long fromAc) {
		this.fromAc = fromAc;
	}
	public long getToAc() {
		return toAc;
	}
	public void setToAc(long toAc) {
		this.toAc = toAc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public FundTransfer(long fromAc, long toAc, double amount) {
		super();
		this.fromAc = fromAc;
		this.toAc = toAc;
		this.amount = amount;
	}
	
	public FundTransfer()
	{
	}
}
