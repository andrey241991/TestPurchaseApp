package com.example.home_pc.testpurchaselistapp.repository;

import android.app.Application;

import com.example.home_pc.testpurchaselistapp.dao.PurchaseDao;
import com.example.home_pc.testpurchaselistapp.db.PurchaseDataBase;
import com.example.home_pc.testpurchaselistapp.entity.Purchase;

import java.util.List;

public class PurchaseRepository {

    private PurchaseDao purchaseDao;

    public PurchaseRepository(Application application) {
        PurchaseDataBase db = PurchaseDataBase.getDatabase(application);
        purchaseDao = db.purchaseDao();
    }

    public Long insert(Purchase purchase) {
        return purchaseDao.insertPurchase(purchase);
    }

    public List<Purchase> getMyPurchases() {
       return purchaseDao.getMyPurchases();
    }

    public List<Purchase> getFuturePurchases() {
        return purchaseDao.getFuturePurchases();
    }

    public void addPurchaseToMyList(long id){
        purchaseDao.addToMyList(id);
    }

}
