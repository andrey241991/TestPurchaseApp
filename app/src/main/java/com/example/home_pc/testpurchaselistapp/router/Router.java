package com.example.home_pc.testpurchaselistapp.router;

import android.content.Context;
import android.content.Intent;

import com.example.home_pc.testpurchaselistapp.ui.addpurchaseactivity.view.AddPurchaseActivity;

public class Router {

    public void startAddNewPurchaseScreen(Context context){
        context.startActivity(new Intent(context, AddPurchaseActivity.class));
    }
}
