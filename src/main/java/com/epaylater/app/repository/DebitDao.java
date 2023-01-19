package com.epaylater.app.repository;

import com.epaylater.app.entities.Debit;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface DebitDao {
    @Transaction
    @SqlUpdate("INSERT INTO debit(customer_id, merchant_id, debit_date, debit_amount, remaining_amount) VALUES (:customer_id, :merchant_id, :debit_date, :debit_amount, :remaining_amount)")
    @GetGeneratedKeys
    int createDebit(@BindBean Debit debit);

    @SqlQuery("SELECT * FROM debit")
    @RegisterBeanMapper(Debit.class)
    List<Debit> getDebits();

    @SqlQuery("SELECT * FROM debit WHERE debit_id = :id")
    @RegisterBeanMapper(Debit.class)
    Debit getDebitById(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM debit WHERE customer_id = :id")
    @RegisterBeanMapper(Debit.class)
    List<Debit> getDebitOfCustomer(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM debit WHERE merchant_id = :id")
    @RegisterBeanMapper(Debit.class)
    List<Debit> getDebitOfMerchant(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM debit WHERE id:= id AND remaining_amount > 0")
    @RegisterBeanMapper(Debit.class)
    List<Debit> getRemainingDebits(@Bind("id") Long id);

    @SqlUpdate("UPDATE debit SET remaining_amount = :amount WHERE debit_id = :id")
    void updateRemainingAmountOfDebit(@Bind("id") Long id, @Bind("amount") Double amount);

    @SqlUpdate("UPDATE debit SET remaining_amount = :amount WHERE customer_id = :id")
    void updateRemainingAmountByCustomer(@Bind("id") Long id, @Bind("amount") Double amount);
}
