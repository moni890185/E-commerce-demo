package com.project.monica.snobsinenobilitate.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.model.Product;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by monica on 17/12/2014.
 */
public class ProductListAdapter extends RecyclerView.Adapter {

    // Fields
    private ArrayList<Product> mDataSet;
    private Matrix mMatrix = null;
    private Context mContext;
    private OnCustomItemClickListener mItemListener;

    public interface OnCustomItemClickListener
    {
        void onClick(int position);
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        Timber.d("onBindViewHolder - price: " + mDataSet.get(position).getPrice());
        Double price = mDataSet.get(position).getPrice();
        mHolder.getCardPriceView().setText("£ "+ price.toString());

        mHolder.getCardDescriptionView().setText(mDataSet.get(position).getImageDescription());

        Timber.d("onBindViewHolder - img_id: " + mDataSet.get(position).getImageDrawable());
        int imgId = mDataSet.get(position).getImageDrawable();
        mHolder.getCardImageView().setImageResource(imgId);
        mHolder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onClick(position);
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
        // Fields - TODO finish to implement

        private final CardView mCardView;
        private final ImageView mCardImageView;
        private final TextView mCardPriceView;
        private final TextView mCardDescriptionView;
        private final LinearLayout mCardPriceLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mCardImageView = (ImageView) itemView.findViewById(R.id.img_card_view);
            mCardPriceView = (TextView) itemView.findViewById(R.id.card_price_text);
            mCardDescriptionView = (TextView) itemView.findViewById(R.id.card_description_text);
            mCardPriceLayout = (LinearLayout) itemView.findViewById(R.id.card_price_container);
        }
        public TextView getCardDescriptionView(){return  mCardDescriptionView;}

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
