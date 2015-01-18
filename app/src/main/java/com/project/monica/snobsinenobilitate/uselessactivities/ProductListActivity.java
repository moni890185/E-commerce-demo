package com.project.monica.snobsinenobilitate.uselessactivities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.adapter.ProductListAdapter;
import com.project.monica.snobsinenobilitate.customrecyclerview.AutofitRecyclerView;
import com.project.monica.snobsinenobilitate.model.Product;

import java.util.ArrayList;


public class ProductListActivity extends Activity {
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
        mRecyclerView = (AutofitRecyclerView) findViewById(R.id.recycler_view_product_list);
        // RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductListAdapter);


    }


    private void initDataset() {
        // MOCK!!!!
        mDataset = new ArrayList<Product>();
        for (int i = 0; i < imgDrawables.length && i < productPrices.length; i++) {
//            Product p = new Product(productPrices[i], imgDrawables[i], i);
//            mDataset.add(p);
        }
    }



}
