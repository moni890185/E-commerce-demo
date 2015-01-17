package com.project.monica.snobsinenobilitate.activities;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.adapter.ProductListAdapter;
import com.project.monica.snobsinenobilitate.customrecyclerview.AutofitRecyclerView;
import com.project.monica.snobsinenobilitate.listener.ScaleGestureListener;
import com.project.monica.snobsinenobilitate.model.Product;

import java.util.ArrayList;

import timber.log.Timber;


public class ProductListActivity extends Activity implements ScaleGestureListener.ScalePinchListener {
    // private CardView mCardViewLeft;
    private AutofitRecyclerView mRecyclerView;

    private View mTransparentView;

    // Mock
    private static int[] imgDrawables = new int[]{R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2, R.drawable.img_card_view_woman1, R.drawable.img_card_view_women, R.drawable.img_card_view_shoes, R.drawable.img_card_view, R.drawable.img_card_view_jacket, R.drawable.img_card_view_man, R.drawable.img_card_view_man1, R.drawable.img_card_view_man2};
    private static double[] productPrices = new double[]{130, 120, 115, 135, 110, 171, 135, 143, 122, 160, 123, 122, 102};

    // Pinch Gesture Detector Obj
    private ScaleGestureDetector mScaleGestureDetector;

    // Adapter
    private ProductListAdapter mProductListAdapter;

    /* Dataset with the images to insert in the cards */
    private ArrayList<Product> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataset();
        initAdapter();
        initScaleGesture();

        mTransparentView = findViewById(R.id.transparent_view);
        mRecyclerView = (AutofitRecyclerView) findViewById(R.id.recycler_view_product_list);
        // RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductListAdapter);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mScaleGestureDetector.onTouchEvent(ev)) {
            Timber.d("scale gesture detector reveled a pinch -  on the transparent view found!!!!!!!");
            return mTransparentView.onTouchEvent(ev);
        }
        Timber.d("scale gesture that is not a pinch zoom found!!!!!!!");
        return mRecyclerView.onTouchEvent(ev);
    }

    private void initDataset() {
        // MOCK!!!!
        mDataset = new ArrayList<Product>();
        for (int i = 0; i < imgDrawables.length && i < productPrices.length; i++) {
            Product p = new Product(productPrices[i], imgDrawables[i]);
            mDataset.add(p);
        }
    }

    private void initAdapter() {
        mProductListAdapter = new ProductListAdapter(this, mDataset);
    }

    private void initScaleGesture() {
        ScaleGestureListener mScaleGestureListener = new ScaleGestureListener();
        // the adapter and the activity itself implement this listener
        mScaleGestureListener.setScalePinchListener(mProductListAdapter, this);
        mScaleGestureDetector = new ScaleGestureDetector(this, mScaleGestureListener);
    }

    @Override
    public void onScaleAction(Matrix matrix) {
        Timber.d("Changing the dimen of column Width");
        mRecyclerView.refreshColumnWidth((int) getResources().getDimension(R.dimen.card_view_width_half));
    }
}
