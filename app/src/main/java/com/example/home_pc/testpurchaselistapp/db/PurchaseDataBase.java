package com.example.home_pc.testpurchaselistapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.home_pc.testpurchaselistapp.dao.PurchaseDao;
import com.example.home_pc.testpurchaselistapp.entity.Purchase;

@Database(entities = {Purchase.class}, version = 1)
public abstract class PurchaseDataBase extends RoomDatabase {

    public static final String NAME =  "PurchaseDataBase";

    public abstract PurchaseDao purchaseDao();

    private static volatile PurchaseDataBase INSTANCE;

    public static PurchaseDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PurchaseDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PurchaseDataBase.class, NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
