package com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.home_pc.testpurchaselistapp.R;
import com.example.home_pc.testpurchaselistapp.ui.main.view.MainActivity;
import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.presenter.PurchaseFragmentPresenter;
import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.adapter.RecyclerItemDecorator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseFragment extends MvpAppCompatFragment implements PurchaseView {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject
    @InjectPresenter
    PurchaseFragmentPresenter presenter;

    @ProvidePresenter
    public PurchaseFragmentPresenter providePresenter(){
        return new PurchaseFragmentPresenter(requireActivity().getApplication());
    }

    public PurchaseFragment() {
        // Required empty public constructor
    }

    public static PurchaseFragment newInstance() {
        PurchaseFragment fragment = new PurchaseFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    public void onTabChanged(int position) {
        presenter.onTabChanged(position);
    }

    public void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new RecyclerItemDecorator());
        recyclerView.setAdapter(presenter.adapter);
    }

    public void hideAddSelectedButton() {
        ((MainActivity)requireActivity()).hideAddSelectedButton();
    }

    public void showAddSelectedButton() {
        ((MainActivity)requireActivity()).showAddSelectedButton();
    }

    public void addSelectedPurchasesToMyList() {
        presenter.addSelectedPurchasesToMyList();
    }
}
