package com.project.monica.snobsinenobilitate.customrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import timber.log.Timber;

/** Resource Lib.:
 * https://github.com/chiuki/android-recyclerview/blob/master/app/src/main/java/com/sqisland/android/recyclerview/AutofitRecyclerView.java
 * Edit by Monica.
 */


public class AutofitRecyclerView extends RecyclerView {

    /* The number of columns in the grid */
    private static final int NUM_COLUMNS_GRID_DEFAULT = 2;

    private GridLayoutManager manager;
    private int columnWidth = -1;

    public AutofitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
        }

        manager = new GridLayoutManager(getContext(),NUM_COLUMNS_GRID_DEFAULT);
        setLayoutManager(manager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            Timber.d("Monica", "onMeasure - columnWidth" + columnWidth);
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            Timber.d("Monica","onMeasure - spanCount"+ spanCount);
            manager.setSpanCount(spanCount);
        }
    }

    public void refreshColumnWidth(int columnWidth)
    {
        Timber.d("Monica","refreshColumnWidth - "+ columnWidth);
        this.columnWidth = columnWidth;

        // to call onMeasure
        requestLayout();
        // to call on Draw
        invalidate();
    }
}