package com.example.home_pc.testpurchaselistapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.home_pc.testpurchaselistapp.entity.Purchase;

import java.util.List;

@Dao
public interface PurchaseDao{

    @Insert
    Long insertPurchase(Purchase purchase);

    @Query("SELECT * FROM purchaseTable WHERE inMyList = 1")
    List<Purchase> getMyPurchases();

    @Query("SELECT * FROM purchaseTable WHERE inMyList = 0")
    List<Purchase> getFuturePurchases();

    @Query("UPDATE purchaseTable SET inMyList = 1 WHERE id = :id")
    void addToMyList(long id);

}
