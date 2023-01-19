package com.epaylater.app.service;

import com.epaylater.app.entities.Customer;
import com.epaylater.app.exception.EntityNotFoundException;
import com.epaylater.app.repository.CustomerDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private Jdbi jdbi;

    public CustomerService(Jdbi jdbi){
        this.customerDao = jdbi.onDemand(CustomerDao.class);
    }

    public int create(Customer customer) throws Exception {
        if(customer.getLoaned_amount() == null){
            customer.setLoaned_amount(0.00);
        }
        return customerDao.createCustomer(customer);
    }

    public List<Customer> getALlCustomers(){
        return customerDao.getCustomers();
    }

    public Customer getById(long id) throws Exception {
        Customer customer = customerDao.getCustomer(id);
        if(ObjectUtils.isEmpty(customer)){
            throw new EntityNotFoundException("Customer with " + id + " does not exists!");
        }
        return customer;
    }

    public String updateCustomerCreditLimit(Long id, Double credit_limit){
        if(customerDao.getCustomer(id) == null)throw new EntityNotFoundException("Customer with id: " + id + " doesn't exists.");
        customerDao.updateCustomerCreditLimit(id, credit_limit);
        return "Customer " + id + "'s credit limit updated!";
    }

    public String updateCustomerLoanedAmount(Long id, Double loaned_amount){
        if(customerDao.getCustomer(id) == null)throw new EntityNotFoundException("Customer with id: " + id + " doesn't exists.");
        customerDao.updateCustomerLoanedAmount(id, loaned_amount);
        return "Customer " + id + "'s loaned amount updated!";
    }

    public Double getCustomerLoanedAmount(Long id){
        if(customerDao.getCustomer(id) == null)throw new EntityNotFoundException("Customer with id: " + id + " doesn't exists.");
        return customerDao.getLoanedAmount(id);
    }

    public String deleteById(long id){
        if(customerDao.getCustomer(id) == null)throw new EntityNotFoundException("Customer with id: " + id + " doesn't exists.");
        customerDao.deleteCustomer(id);
        return "Customer Deleted!";
    }
}