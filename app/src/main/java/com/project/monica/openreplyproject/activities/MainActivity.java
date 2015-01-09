package com.project.monica.openreplyproject.activities;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.project.monica.openreplyproject.R;
import com.project.monica.openreplyproject.adapter.ProductListAdapter;
import com.project.monica.openreplyproject.customrecyclerview.AutofitRecyclerView;
import com.project.monica.openreplyproject.listener.ScaleGestureListener;


public class MainActivity extends Activity implements ScaleGestureListener.ScalePinchListener {
    // private CardView mCardViewLeft;
    private AutofitRecyclerView mRecyclerView;
    private ScaleGestureDetector mScaleGestureDetector;
    private ProductListAdapter mProductListAdapter;

    /* dataset with the images to insert in the cards */
    private int[] mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initDataset();
        initAdapter();
        initScaleGesture();

        mRecyclerView = (AutofitRecyclerView) findViewById(R.id.recycler_view_product_list);
        // RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new RecyclerView.ItemAnimator() {
            @Override
            public void runPendingAnimations() {

            }

            @Override
            public boolean animateRemove(RecyclerView.ViewHolder holder) {
                return false;
            }

            @Override
            public boolean animateAdd(RecyclerView.ViewHolder holder) {
                return false;
            }

            @Override
            public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                return false;
            }

            @Override
            public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                return false;
            }

            @Override
            public void endAnimation(RecyclerView.ViewHolder item) {

            }

            @Override
            public void endAnimations() {

            }

            @Override
            public boolean isRunning() {
                return false;
            }
        });
        mRecyclerView.setAdapter(mProductListAdapter);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mScaleGestureDetector.onTouchEvent(ev);
        return true;
    }

    private void initDataset() {
        mDataset = new int[]{R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2, R.drawable.img_card_view_woman1, R.drawable.img_card_view_women, R.drawable.img_card_view_shoes, R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2};
    }

    private void initAdapter() {
        mProductListAdapter = new ProductListAdapter(this, mDataset);
    }

    private void initScaleGesture() {
        ScaleGestureListener mScaleGestureListener = new ScaleGestureListener();
        // the adapter implements the listener
        mScaleGestureListener.setScalePinchListener(mProductListAdapter, this);
        mScaleGestureDetector = new ScaleGestureDetector(this, mScaleGestureListener);
    }

    @Override
    public void onScaleAction(Matrix matrix) {
        Log.d("Monica", "Changing the dimen of column Width");
        mRecyclerView.refreshColumnWidth((int) getResources().getDimension(R.dimen.card_view_width_half));
    }
}
