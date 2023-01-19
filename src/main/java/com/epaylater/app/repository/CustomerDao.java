package com.epaylater.app.repository;

import com.epaylater.app.entities.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface CustomerDao{
    @Transaction
    @SqlUpdate("INSERT INTO customer(name, address, emailId, contact_number, credit_limit, loaned_amount) VALUES(:name, :address, :emailId, :contact_number, :credit_limit, :loaned_amount)")
    @GetGeneratedKeys
    int createCustomer(@BindBean Customer customer);

    @SqlQuery("SELECT * FROM customer")
    @RegisterBeanMapper(Customer.class)
    List<Customer> getCustomers();

    @SqlQuery("SELECT * FROM customer WHERE customer_id = :id")
    @RegisterBeanMapper(Customer.class)
    Customer getCustomer(@Bind("id") Long id);

    @SqlUpdate("UPDATE customer SET credit_limit = :limit WHERE customer_id = :id")
    void updateCustomerCreditLimit(@Bind("id") Long id,@Bind("limit") Double limit);

    @SqlUpdate("UPDATE customer SET loaned_amount = :loaned_amount WHERE customer_id = :id")
    void updateCustomerLoanedAmount(@Bind("id") Long id, @Bind("loaned_amount") Double loaned_amount);

    @SqlQuery("SELECT loaned_amount FROM customer WHERE customer_id = :id")
    Double getLoanedAmount(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM customer WHERE customer_id = :id")
    void deleteCustomer(@Bind("id") Long id);
}
