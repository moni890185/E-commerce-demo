package com.project.monica.snobsinenobilitate.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.model.Product;

import java.util.ArrayList;

/**
 * Created by monica on 17/12/2014.
 */
public class ProductListAdapter extends RecyclerView.Adapter {

    // Fields
    private Context mContext;
    private ArrayList<Product> mDataSet;
    private OnCustomItemClickListener mItemListener;

    public interface OnCustomItemClickListener {
        void onItemClick(Integer productId);
    }

    // Constructor
    public ProductListAdapter(Context context, ArrayList<Product> dataset, OnCustomItemClickListener itemListener) {
        mDataSet = dataset;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_card_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        Product product = mDataSet.get(position);

        String price = product.getPrice();
        int imgId = product.getImageId();

        // insert Content
        mHolder.getCardPriceView().setText(TextUtils.concat(mContext.getResources().getString(R.string.price_currency), price));
        mHolder.getCardTitleView().setText(product.getTitle());
        mHolder.getCardImageView().setImageResource(imgId);

        mHolder.getCardView().setTag(product.getId());
        mHolder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer productId = (Integer) v.getTag();
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
