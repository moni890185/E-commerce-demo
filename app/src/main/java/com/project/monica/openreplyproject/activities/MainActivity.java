package com.project.monica.openreplyproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.project.monica.openreplyproject.R;
import com.project.monica.openreplyproject.adapter.ProductListAdapter;
import com.project.monica.openreplyproject.customrecyclerview.AutofitRecyclerView;


public class MainActivity extends Activity {
    // private CardView mCardViewLeft;
    private AutofitRecyclerView mRecyclerView;

    // Adapter
    private ProductListAdapter mProductListAdapter;

    /* dataset with the images to insert in the cards */
    private int[] mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initDataset();
        initAdapter();

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

    private void initDataset() {
        mDataset = new int[]{R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2, R.drawable.img_card_view_woman1, R.drawable.img_card_view_women, R.drawable.img_card_view_shoes, R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2};
    }

    private void initAdapter() {
        mProductListAdapter = new ProductListAdapter(this, mDataset);
    }


}
