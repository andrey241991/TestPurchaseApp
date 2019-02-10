package com.example.home_pc.testpurchaselistapp.ui.purchasefragment.presenter;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.home_pc.testpurchaselistapp.entity.Purchase;
import com.example.home_pc.testpurchaselistapp.repository.PurchaseRepository;
import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.PurchaseView;
import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.adapter.PurchaseAdapter;
import com.example.home_pc.testpurchaselistapp.utils.ImageConverterHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.home_pc.testpurchaselistapp.ui.main.view.adapter.MainAdapter.FUTURE_PURCHASES;
import static com.example.home_pc.testpurchaselistapp.ui.main.view.adapter.MainAdapter.MY_PURCHASES;


@InjectViewState
public class PurchaseFragmentPresenter extends MvpPresenter<PurchaseView> {

    public ArrayList<Purchase> itemList;
    public PurchaseAdapter adapter;
    private int selectedTab;
    private HashSet<Purchase> selectedItems;
    private PurchaseRepository repository;
    private final static int SELECTED = 1;
    private final static int NOT_SELECTED = 0;


    public PurchaseFragmentPresenter(Application application) {
        itemList = new ArrayList<>();
        adapter = new PurchaseAdapter(this);
        selectedItems = new HashSet<>();
        repository = new PurchaseRepository(application);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        new GetMyPurchasesAsynkTask().execute();
    }

    public void onTabChanged(int position) {
        switch (position) {
            case MY_PURCHASES:
                selectedTab = MY_PURCHASES;
                new GetMyPurchasesAsynkTask().execute();
                break;

            case FUTURE_PURCHASES:
                selectedTab = FUTURE_PURCHASES;
                new GetFuturePurchasesAsynkTask().execute();
                break;
        }
    }

    public void onBindRepositoryRowViewAtPosition(int position, PurchaseAdapter.ViewHolder holder) {
        if (itemList.isEmpty()) {
            return;
        }
        Purchase currentPurchase = itemList.get(position);
        holder.setTitle(currentPurchase.getTitle());
        holder.setPrice(String.valueOf(currentPurchase.getPrice()));

        Bitmap imageBitmap = ImageConverterHelper.convertByteArrayToBitmap(currentPurchase.getImage());
        holder.setImage(imageBitmap);

        if(currentPurchase.getSelected() == SELECTED){
            holder.selectItem();
        }else {
            holder.unselectItem();
        }

    }

    public void onItemClick(int position, PurchaseAdapter.ViewHolder holder) {
        Log.v("TAG", "position item click =" + position);
        if (selectedTab != FUTURE_PURCHASES) {
            return;
        }

        Purchase currentPurchase = itemList.get(position);
        if (currentPurchase.getSelected() == SELECTED) {
            selectedItems.remove(currentPurchase);
            currentPurchase.setSelected(NOT_SELECTED);
            adapter.notifyItemChanged(position);
        } else {
            selectedItems.add(currentPurchase);
            currentPurchase.setSelected(SELECTED);
            adapter.notifyItemChanged(position);
        }

        if (selectedItems.isEmpty()) {
            getViewState().hideAddSelectedButton();
        } else {
            getViewState().showAddSelectedButton();
        }
    }

    public void addSelectedPurchasesToMyList() {
        Log.v("TAG", "addSelectedPurchasesToMyList");
        for (Purchase purchase : selectedItems){
            new AddPurchaseToMyListAsynkTask().execute(purchase);
        }
       new GetFuturePurchasesAsynkTask().execute();
    }

    private class GetFuturePurchasesAsynkTask extends AsyncTask<Void, Void, List<Purchase>> {
        @Override
        protected List<Purchase> doInBackground(Void... voids) {
            return repository.getFuturePurchases();
        }

        @Override
        protected void onPostExecute(List<Purchase> purchases) {
            itemList = (ArrayList<Purchase>) purchases;
            adapter.notifyDataSetChanged();
        }
    }

    private class GetMyPurchasesAsynkTask extends AsyncTask<Void, Void, List<Purchase>> {
        @Override
        protected List<Purchase> doInBackground(Void... voids) {
            return repository.getMyPurchases();
        }

        @Override
        protected void onPostExecute(List<Purchase> purchases) {
            itemList = (ArrayList<Purchase>) purchases;
            adapter.notifyDataSetChanged();
        }
    }

    private class AddPurchaseToMyListAsynkTask extends AsyncTask<Purchase, Void, Void> {
        @Override
        protected Void doInBackground(Purchase... purchases) {
            repository.addPurchaseToMyList(purchases[0].getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
