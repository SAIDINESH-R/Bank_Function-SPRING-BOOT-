package Dinesh.bank.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Dinesh.bank.domain.Account;
import Dinesh.bank.domain.FundTransfer;
import Dinesh.bank.CustomerException.AccountNotFound;
import Dinesh.bank.CustomerException.ExceptionMessage;
import Dinesh.bank.dao.AccountRepository;

@Service
public class TransferService {
	@Autowired
	AccountRepository accountrepo;

	public String amountTransfer(FundTransfer fundtransfer) throws AccountNotFound {
		if (fundtransfer.getAmount() > 0) {

			Account debitAc = accountrepo.findByAccNo(fundtransfer.getFromAc());
			if (debitAc != null) {
				Account creditAc = accountrepo.findByAccNo(fundtransfer.getToAc());
				if (creditAc != null) {
					if(debitAc.getAccNo() != creditAc.getAccNo()) {
					if (debitAc.getAccBalance() > fundtransfer.getAmount()) {
						creditAc.setAccBalance(creditAc.getAccBalance() + fundtransfer.getAmount());
						debitAc.setAccBalance(debitAc.getAccBalance() - fundtransfer.getAmount());
						accountrepo.save(creditAc);
						accountrepo.save(debitAc);

						return "Rs." + fundtransfer.getAmount() + " Transferred Successfully from A/c No."
								+ debitAc.getAccNo() + " to A/C No." + creditAc.getAccNo()
								+ ". Updated Account balance:- A/c No. " + creditAc.getAccNo() + " = "
								+ creditAc.getAccBalance() + ", A/c No. " + debitAc.getAccNo() + " = "
								+ debitAc.getAccBalance();
					}
					//return "Oops! Not Enough fund!";
					throw new AccountNotFound("Oops! Not Enough fund!");
				}
					//return "Same account transfer is not allowed!";
					throw new AccountNotFound("Same account transfer is not allowed!");
					
				}
				//return "Receiver Account doesn't exist!";
				throw new AccountNotFound("Receiver Account doesn't exist!");
			}
			//return "Transfer Account doesn't exist!";
			throw new AccountNotFound("Transfer Account doesn't exist!");
		}
		//return "Amount is invalid";
		throw new AccountNotFound("Amount is invalid" );
	}
}
