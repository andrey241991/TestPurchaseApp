package com.example.home_pc.testpurchaselistapp.ui.main.presenter;

import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.home_pc.testpurchaselistapp.ui.main.view.MainView;
import com.example.home_pc.testpurchaselistapp.ui.main.view.adapter.MainAdapter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public MainAdapter adapter;

    public MainPresenter(FragmentManager supportFragmentManager) {
        this.adapter = new MainAdapter(supportFragmentManager);
    }

    public void onPageSelected(int position) {
        switch (position){
            case 0:
                getViewState().hideFloatButton();
                getViewState().hideAddSelectedButton();
                break;
            case 1:
                getViewState().showFloatButton();
                break;
        }
    }
}
