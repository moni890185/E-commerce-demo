package com.project.monica.snobsinenobilitate.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.models.pojo.Product;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by monica on 17/12/2014.
 */
public class ProductListAdapter extends RecyclerView.Adapter {

  // Fields
  private Context mContext;
  private List<Product> mDataSet;
  private OnCustomItemClickListener mItemListener;

  public interface OnCustomItemClickListener {
    void onItemClick(String productId);
  }

  // Constructor
  public ProductListAdapter(Context context, List<Product> dataset,
      OnCustomItemClickListener itemListener) {
    mDataSet = dataset;
    mContext = context;
    mItemListener = itemListener;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.product_list_card_view, parent, false);

    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ViewHolder mHolder = (ViewHolder) holder;
    Product product = mDataSet.get(position);
    Logger.d("product: " + product.getName());

    //// insert Content
    mHolder.getCardPriceView().setText(product.getPriceLabel());
    mHolder.getCardTitleView().setText(product.getName());

    String imageUrl = product.getImage().getSizes().getOriginal().getUrl();
    Logger.d("img url: "+ imageUrl);

    Picasso.with(mContext).load(imageUrl).into(mHolder.getCardImageView());

    mHolder.getCardView().setTag(product.getCode());
    mHolder.getCardView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String productId = (String) v.getTag();
        mItemListener.onItemClick(productId);
      }
    });
  }

  @Override
  public int
  getItemCount() {
    return mDataSet.size();
  }

  // ViewHolder
  private static class ViewHolder extends RecyclerView.ViewHolder {

    private final CardView mCardView;
    private final ImageView mCardImageView;
    private final TextView mCardPriceView;
    private final TextView mCardTitleView;

    public ViewHolder(View itemView) {
      super(itemView);
      mCardView = (CardView) itemView.findViewById(R.id.card_view);
      mCardImageView = (ImageView) itemView.findViewById(R.id.img_card_view);
      mCardPriceView = (TextView) itemView.findViewById(R.id.card_price_text);
      mCardTitleView = (TextView) itemView.findViewById(R.id.card_title_text);
    }

    public CardView getCardView() {
      return mCardView;
    }

    public ImageView getCardImageView() {
      return mCardImageView;
    }

    public TextView getCardPriceView() {
      return mCardPriceView;
    }

    public TextView getCardTitleView() {
      return mCardTitleView;
    }
  }
}
