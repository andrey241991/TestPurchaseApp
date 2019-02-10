package com.example.home_pc.testpurchaselistapp.ui.main.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.home_pc.testpurchaselistapp.R;
import com.example.home_pc.testpurchaselistapp.app.App;
import com.example.home_pc.testpurchaselistapp.ui.main.presenter.MainPresenter;
import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.PurchaseFragment;
import com.example.home_pc.testpurchaselistapp.router.Router;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.txtAddSelected) TextView txtAddSelected;

    @Inject
    public Router router;

    @InjectPresenter
    public MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter(){
        return new MainPresenter(getSupportFragmentManager());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getDaggerAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupViewPager();

        fab.setOnClickListener(view ->
                router.startAddNewPurchaseScreen(this)
        );

        txtAddSelected.setOnClickListener(view ->
                   presenter.adapter.addPurchasesToMyList()
         );
    }

    private void setupViewPager() {
        presenter.adapter.addFragment(PurchaseFragment.newInstance(), getString(R.string.my_purchases));
        presenter.adapter.addFragment(PurchaseFragment.newInstance(), getString(R.string.future_purchases));
        viewPager.setAdapter(presenter.adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                presenter.onPageSelected(i);
                presenter.adapter.onPageSelected(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) { }
        });

    }

    @Override
    public void hideFloatButton() {
        fab.hide();
    }

    @Override
    public void showFloatButton() {
        fab.show();
    }

    public void hideAddSelectedButton() {
        txtAddSelected.setVisibility(View.GONE);
    }

    public void showAddSelectedButton() {
        txtAddSelected.setVisibility(View.VISIBLE);
    }


}
