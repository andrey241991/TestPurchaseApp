package com.example.home_pc.testpurchaselistapp.ui.addpurchaseactivity.presenter;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.home_pc.testpurchaselistapp.entity.Purchase;
import com.example.home_pc.testpurchaselistapp.repository.PurchaseRepository;
import com.example.home_pc.testpurchaselistapp.ui.addpurchaseactivity.view.AddPurchaseView;
import com.example.home_pc.testpurchaselistapp.utils.ImageConverterHelper;

@InjectViewState
public class AddPurchasePresenter extends MvpPresenter<AddPurchaseView> {

    private PurchaseRepository repository;

    public AddPurchasePresenter(Application application) {
        repository = new PurchaseRepository(application);
    }

    public void onSubmitBtnClick(String title, String price, Bitmap imageBitmap) {

        if (title.isEmpty() || price.isEmpty() || imageBitmap == null) {
            getViewState().showFieldsErrorMessage();
            return;
        }

        byte[] imageByteArray = ImageConverterHelper.convertBitmapToByteArray(imageBitmap);
        Purchase purchase = new Purchase(title, Integer.parseInt(price), imageByteArray);
        new InsertPurchaseAsynkTask().execute(purchase);
    }


    private class InsertPurchaseAsynkTask extends AsyncTask<Purchase, Void, Long> {
        @Override
        protected Long doInBackground(Purchase... purchases) {
            Purchase purchase = purchases[0];
            return repository.insert(purchase);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Log.v("TAG", "purchases id = " + aLong);
        }
    }


}
