package com.epaylater.app.repository;

import com.epaylater.app.entities.Customer;
import com.epaylater.app.entities.Merchant;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface MerchantDao {
    @Transaction
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO merchant(name, address, emailId, contact_number) VALUES(:name, :address, :emailId, :contact_number)")
    int createMerchant(@BindBean Merchant merchant);

    @SqlQuery("SELECT * FROM merchant")
    @RegisterBeanMapper(Merchant.class)
    List<Merchant> getMerchants();

    @SqlQuery("SELECT * FROM merchant WHERE merchant_id = :id")
    @RegisterBeanMapper(Merchant.class)
    Merchant getMerchant(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM merchant WHERE merchant_id = :id")
    void deleteMerchant(@Bind("id") Long id);
}
