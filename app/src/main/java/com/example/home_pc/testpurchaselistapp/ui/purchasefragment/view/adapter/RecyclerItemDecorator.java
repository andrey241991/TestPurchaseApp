package com.example.home_pc.testpurchaselistapp.ui.purchasefragment.view.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerItemDecorator extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = 15;
        outRect.right = 15;
        outRect.left = 15;
    }
}

