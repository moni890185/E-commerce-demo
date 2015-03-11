package com.project.monica.openreplyproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.project.monica.openreplyproject.R;
import com.project.monica.openreplyproject.adapter.ProductListAdapter;
import com.project.monica.openreplyproject.adapter.ProductSwipeCardAdapter;
import com.project.monica.openreplyproject.model.Product;
import java.util.ArrayList;

public class ProductListSwipeActivity extends Activity implements
    ProductListAdapter.OnCustomClickListener {

  /* Dataset with the images to insert in the cards */
  private CardContainer mCardContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_swipe);
    getActionBar().hide();
    mCardContainer = (CardContainer) findViewById(R.id.card_container_id);
    ArrayList<CardModel> cardModels = new ArrayList<CardModel>();
    populateCardModels(cardModels);
    mCardContainer.setAdapter(new ProductSwipeCardAdapter(this, cardModels));
  }

  private void populateCardModels(ArrayList<CardModel> cardModels) {
    Product[] mDataset = Product.PRODUCTS;
    for (Product p : mDataset) {
      CardModel tCardModel = new CardModel(p.getTitle(), p.getPrice(), getDrawable(p.getImageDrawable()));
      tCardModel.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
        @Override public void onLike() {
          Log.d("Monica","LIKED!");
        }

        @Override public void onDislike() {
          Log.d("Monica","DISLIKED!");
        }

        @Override public void onAddToBasket() {
          Log.d("Monica","ADDED TO YOUR CART!");
        }
      });
      cardModels.add(tCardModel);
    }
  }



  @Override
  public void onClick(Integer productId, final View view) {
    Intent intent = new Intent(this, ProductDetailActivity.class);
    intent.putExtra(ProductDetailActivity.PRODUCT_ID, productId);

    // inserting transition animation.
    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
        new Pair<View, String>(view.findViewById(R.id.card_price_text),
            ProductDetailActivity.VIEW_TRANSITION_PRICE),
        new Pair<View, String>(view.findViewById(R.id.card_title_text),
            ProductDetailActivity.VIEW_TRANSITION_TITLE),
        new Pair<View, String>(view.findViewById(R.id.img_card_view),
            ProductDetailActivity.VIEW_TRANSITION_IMAGE),
        new Pair<View, String>(view.findViewById(R.id.card_price_container),
            ProductDetailActivity.VIEW_TRANSITION_HOLDER_PRICE));

    //Animation slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

    ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
  }
}
