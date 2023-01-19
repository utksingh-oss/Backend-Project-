package com.epaylater.app.repository;

import com.epaylater.app.entities.Credit;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface CreditDao {
    @Transaction
    @SqlUpdate("INSERT INTO credit(debit_id, customer_id, credit_date,credit_amount) VALUES(:debit_id, :customer_id, :credit_date,:credit_amount)")
    @GetGeneratedKeys
    int createCredit(@BindBean Credit credit);

    @SqlQuery("SELECT * FROM credit")
    @RegisterBeanMapper(Credit.class)
    List<Credit> getCredits();

    @SqlQuery("SELECT * FROM credit WHERE customer_id = :id")
    @RegisterBeanMapper(Credit.class)
    Credit getCreditById(@Bind("id") Long id);

}
