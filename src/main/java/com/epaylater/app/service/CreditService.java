package com.epaylater.app.service;


import com.epaylater.app.entities.Credit;
import com.epaylater.app.entities.Customer;
import com.epaylater.app.entities.Debit;
import com.epaylater.app.repository.CreditDao;
import com.epaylater.app.repository.CustomerDao;
import com.epaylater.app.repository.DebitDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private CreditDao creditDao;
    private CustomerDao customerDao;
    private DebitDao debitDao;
    private Jdbi jdbi;

    public CreditService(Jdbi jdbi){
        this.creditDao = jdbi.onDemand(CreditDao.class);
        this.customerDao = jdbi.onDemand(CustomerDao.class);
        this.debitDao = jdbi.onDemand(DebitDao.class);
    }

    public int addCredit(Credit credit) throws Exception {
        Debit debit = debitDao.getDebitById(credit.getDebit_id());
        if(debit == null)throw new Exception();
        if(credit.getCredit_amount() > debit.getRemaining_amount())throw new Exception(); //implement
        debitDao.updateRemainingAmountOfDebit(credit.getDebit_id(),debit.getRemaining_amount() - credit.getCredit_amount());
        Customer customer = customerDao.getCustomer(credit.getCustomer_id());
        customerDao.updateCustomerLoanedAmount(credit.getCustomer_id(),customer.getLoaned_amount() - credit.getCredit_amount());
        int id = creditDao.createCredit(credit);
        return id;
    }

    //function to clear all the debits
    public void creditToClearAllDues(Credit credit) throws Exception { //removing debitId NOT NULL constraint
        Customer customer = customerDao.getCustomer(credit.getCustomer_id());
        if(credit.getCredit_amount() < customer.getLoaned_amount())throw new Exception(); //implement
        debitDao.updateRemainingAmountByCustomer(customer.getCustomer_id(), (double)0);
        customerDao.updateCustomerLoanedAmount(customer.getCustomer_id(), (double)0);
        creditDao.createCredit(credit);
    }

}
