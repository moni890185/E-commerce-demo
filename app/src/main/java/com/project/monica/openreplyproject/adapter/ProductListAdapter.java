package com.project.monica.openreplyproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.monica.openreplyproject.R;

/**
 * Created by monica on 17/12/2014.
 */
public class ProductListAdapter extends RecyclerView.Adapter {

    // Fields
    private int[] mDataSet;
    private Context mContext;


    // Constructor
    public ProductListAdapter(Context context, int[] dataset) {
        mDataSet = dataset;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_card_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;

        int img_id = mDataSet[position];
        mHolder.getCardImageView().setImageResource(img_id);
    }

    @Override
    public int
    getItemCount() {
        return mDataSet.length;
    }


    // ViewHolder
    private static class ViewHolder extends RecyclerView.ViewHolder {


        // Fields - TODO finish to implement
        private final CardView mCardView;
        private final ImageView mCardImageView;
        private final TextView mCardPriceView;
        private final LinearLayout mCardPriceLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mCardImageView = (ImageView) itemView.findViewById(R.id.img_card_view);
            mCardPriceView = (TextView) itemView.findViewById(R.id.card_price_text);
            mCardPriceLayout = (LinearLayout) itemView.findViewById(R.id.card_price_container);
        }

        public CardView getCardView() {
            return mCardView;
        }

        public ImageView getCardImageView() {
            return mCardImageView;
        }

        public LinearLayout getCardPriceContainerView() {
            return mCardPriceLayout;
        }

        public TextView getCardPriceView() {
            return mCardPriceView;
        }
    }

}
