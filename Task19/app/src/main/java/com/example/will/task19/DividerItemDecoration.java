package com.example.will.task19;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

@SuppressWarnings({"CanBeFinal", "UnnecessarilyQualifiedInnerClassAccess", "FieldMayBeFinal", "UnnecessaryThis", "ImplicitCallToSuper"})
class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    @SuppressWarnings("SameParameterValue")
    DividerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = this.space;
        outRect.right = this.space;


        //for vertical scrolling
        outRect.bottom = this.space;
        outRect.top = this.space;
    }
}
