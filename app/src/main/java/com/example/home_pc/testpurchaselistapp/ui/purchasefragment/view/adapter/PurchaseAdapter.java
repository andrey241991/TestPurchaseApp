package com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_pc.testpurchaselistapp.R;
import com.example.home_pc.testpurchaselistapp.ui.purchasefragment.presenter.PurchaseFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    private PurchaseFragmentPresenter presenter;

    public PurchaseAdapter(PurchaseFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.purchase_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        presenter.onBindRepositoryRowViewAtPosition(i, viewHolder);
    }

    @Override
    public int getItemCount() {
        return presenter.itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title) TextView txtTitle;
        @BindView(R.id.txt_price) TextView txtPrice;
        @BindView(R.id.img) ImageView imageView;
        @BindView(R.id.card_view) CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> presenter.onItemClick(getAdapterPosition(), ViewHolder.this));
        }

        public void setTitle(String title){
            txtTitle.setText(title);
        }
        public void setPrice(String price){
            txtPrice.setText(price);
        }
        public void setImage(Bitmap imageBm){
            imageView.setImageBitmap(imageBm);
        }

        public void unselectItem() {
            cardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.colorPrimary));
        }

        public void selectItem() {
            cardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.itemSelected));
        }
    }
}
