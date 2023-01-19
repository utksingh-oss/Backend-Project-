package com.epaylater.app.service;

import com.epaylater.app.entities.Merchant;
import com.epaylater.app.repository.MerchantDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class MerchantService {
    private final MerchantDao merchantDao;
    private Jdbi jdbi;

    public MerchantService(Jdbi jdbi){
        this.merchantDao = jdbi.onDemand(MerchantDao.class);
    }


    public int create(Merchant merchant) throws Exception {
        return merchantDao.createMerchant(merchant);
    }

    public List<Merchant> getAllMerchants(){
        return merchantDao.getMerchants();
    }

    public Merchant getById(long id) throws Exception {
        Merchant merchant = merchantDao.getMerchant(id);
        if(ObjectUtils.isEmpty(merchant)){
            throw new Exception();
        }
        return merchant;
    }

    public String deleteById(long id){
        merchantDao.deleteMerchant(id);
        return "Merchant Deleted!";
    }
}
