package com.project.monica.openreplyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;
import com.project.monica.openreplyproject.R;
import java.util.ArrayList;

/**
 * Created by monica on 10/03/2015.
 */
public class ProductSwipeCardAdapter extends CardStackAdapter{

  public ProductSwipeCardAdapter(Context context, ArrayList<CardModel> cardCollections) {
    super(context, cardCollections);
  }

  @Override public View getCardView(int i, CardModel cardModel, View view, ViewGroup viewGroup) {
    CardViewHolder mHolder;

    if (view == null) {
      view = LayoutInflater.from(getContext()).inflate(R.layout.swipe_list_item, viewGroup, false);
      mHolder = new CardViewHolder();
      mHolder.img = (ImageView) view.findViewById(R.id.swipe_image);
      mHolder.title = (TextView) view.findViewById(R.id.swipe_title);
      mHolder.price = (TextView) view.findViewById(R.id.swipe_price);
      view.setTag(mHolder);
    } else {
      mHolder = (CardViewHolder) view.getTag();
    }

    CardModel cm = (CardModel) getItem(i);
    mHolder.img.setImageDrawable(cm.getCardImageDrawable());
    mHolder.price.setText(cm.getDescription());
    mHolder.title.setText(cm.getTitle());
    return view;
  }

  private static final class CardViewHolder {
    ImageView img;
    TextView title;
    TextView price;
  }
}
