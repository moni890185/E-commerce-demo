package com.project.monica.openreplyproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.monica.openreplyproject.R;
import com.project.monica.openreplyproject.model.Product;

public class ProductDetailActivity extends Activity {

    public final static String PRODUCT_ID = "product:id";
    public final static String VIEW_CARD = "product:card";
    public final static String VIEW_TRANSITION_IMAGE ="product:card:image";
    public final static String VIEW_TRANSITION_PRICE = "product:card:price";
    public final static String VIEW_TRANSITION_HOLDER_PRICE = "product:card:holder:price";

    private ImageView mImage;
    private TextView mPrice;
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        mImage = (ImageView) findViewById(R.id.product_detail_img);
        mPrice = (TextView) findViewById(R.id.product_detail_price);

        ViewCompat.setTransitionName(mImage,VIEW_TRANSITION_IMAGE);
        ViewCompat.setTransitionName(mPrice,VIEW_TRANSITION_PRICE);


        initData();
        insertContent();

    }

    private void insertContent() {

        // for lollipop only
        Transition transition = getWindow().getSharedElementEnterTransition();
        if(transition!=null)
        {
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    loadBigImage();
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }

        mPrice.setText(mProduct.getmPrice());
        // the smaill image
        mImage.setImageResource(mProduct.getImageDrawable());


    }

    private void loadBigImage() {
        // TODO insert the big image
        mImage.setImageResource(mProduct.getImageDrawable());
    }

    private void initData() {
        Integer productId = getIntent().getIntExtra(PRODUCT_ID, 0);
        mProduct = Product.getProduct(productId);
    }
}
