package com.project.monica.openreplyproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;

import com.project.monica.openreplyproject.R;
import com.project.monica.openreplyproject.adapter.ProductListAdapter;
import com.project.monica.openreplyproject.customrecyclerview.AutofitRecyclerView;
import com.project.monica.openreplyproject.listener.ScaleGestureListener;
import com.project.monica.openreplyproject.model.Product;

import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;


public class MainActivity extends Activity implements ScaleGestureListener.ScalePinchListener, ProductListAdapter.OnCustomClickListener {
    // private CardView mCardViewLeft;
    private AutofitRecyclerView mRecyclerView;

    private View mTransparentView;

    // Mock


    // Pinch Gesture Detector Obj
    private ScaleGestureDetector mScaleGestureDetector;

    // Adapter
    private ProductListAdapter mProductListAdapter;

    /* Dataset with the images to insert in the cards */
    private ArrayList<Product> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

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
        mDataset = new ArrayList<Product>(Arrays.asList(Product.PRODUCTS));

    }

    private void initAdapter() {
        mProductListAdapter = new ProductListAdapter(this, mDataset, this);
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

    @Override
    public void onClick(Integer productId, View view) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.PRODUCT_ID, productId);
        // inserting transition animation.

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair<View, String>(view.findViewById(R.id.img_card_view), ProductDetailActivity.VIEW_TRANSITION_IMAGE), new Pair<View, String>(view.findViewById(R.id.card_price_text), ProductDetailActivity.VIEW_TRANSITION_PRICE), new Pair<View, String>(view.findViewById(R.id.card_price_container), ProductDetailActivity.VIEW_TRANSITION_HOLDER_PRICE)
        );

//        the view
//        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
//
//        enter
//        getWindow().setAllowEnterTransitionOverlap(true);

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}