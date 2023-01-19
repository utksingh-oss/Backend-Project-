package com.epaylater.app.service;

import com.epaylater.app.entities.Customer;
import com.epaylater.app.entities.Debit;
import com.epaylater.app.repository.CustomerDao;
import com.epaylater.app.repository.DebitDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitService{
    private DebitDao debitDao;
    private CustomerDao customerDao;
    private Jdbi jdbi;

    public DebitService(Jdbi jdbi){
        this.debitDao = jdbi.onDemand(DebitDao.class);
        this.customerDao = jdbi.onDemand(CustomerDao.class);
    }


    public int addDebit(Debit debit) throws Exception {
        Customer customer = customerDao.getCustomer(debit.getCustomer_id());
        Double creditLimit = customer.getCredit_limit();
        Double loanedAmount = customer.getLoaned_amount();
        Double currentAmount = debit.getDebit_amount();
        loanedAmount = loanedAmount == null ? 0 : loanedAmount;
        int id = -1;
        if(currentAmount > creditLimit - loanedAmount){
            throw new Exception();
        }else{
            customerDao.updateCustomerLoanedAmount(customer.getCustomer_id(), loanedAmount + currentAmount);
            if(debit.getRemaining_amount() == null){
                debit.setRemaining_amount(debit.getDebit_amount());
            }
            id = debitDao.createDebit(debit);
        }
        return id;
    }

    public void getAllRemainingDebits(Long id){
        List<Debit> debits = debitDao.getRemainingDebits(id);
    }

    public List<Debit> getAllCreditsOfCustomer(Long id){
        return debitDao.getDebitOfCustomer(id);
    }

    public Debit getDebitById(Long id){
        return debitDao.getDebitById(id);
    }

    public void updateRemainingAmount(Long id, Double amount){
        debitDao.updateRemainingAmountOfDebit(id, amount);
    }
}
