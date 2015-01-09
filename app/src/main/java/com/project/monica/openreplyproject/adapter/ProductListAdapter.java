package com.project.monica.openreplyproject.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.monica.openreplyproject.R;
import com.project.monica.openreplyproject.listener.ScaleGestureListener;
import com.project.monica.openreplyproject.model.Product;

import java.util.ArrayList;

/**
 * Created by monica on 17/12/2014.
 */
public class ProductListAdapter extends RecyclerView.Adapter implements ScaleGestureListener.ScalePinchListener {

    // Fields
    private ArrayList<Product> mDataSet;
    private Matrix mMatrix = null;
    private Context mContext;


    // Constructor
    public ProductListAdapter(Context context, ArrayList<Product> dataset) {
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
        Log.d("Monica", "onBindViewHolder - price: " + mDataSet.get(position).getPrice());
        Double price = mDataSet.get(position).getPrice();
        mHolder.getCardPriceView().setText("£ "+ price.toString());

        Log.d("Monica", "onBindViewHolder - img_id: " + mDataSet.get(position).getImageDrawable());
        int imgId = mDataSet.get(position).getImageDrawable();
        mHolder.getCardImageView().setImageResource(imgId);


        if (mMatrix != null) {
            Log.d("Monica", "NOT USING MATRIX - but resizing");
            // Card view size
            float cardWidth = mContext.getResources().getDimension(R.dimen.card_view_width_half);
            float cardHeight = mContext.getResources().getDimension(R.dimen.card_view_height_half);
            mHolder.getCardView().setLayoutParams(new FrameLayout.LayoutParams((int)cardWidth, (int)cardHeight));

            // Image size
            int imgWidth = mHolder.getCardImageView().getWidth();
            int imgHeight = mHolder.getCardImageView().getHeight();
            mHolder.getCardImageView().setMaxWidth(imgWidth - 20);
            mHolder.getCardImageView().setMaxHeight(imgHeight - 20);

            // Card Price container size
            int cardPriceContainerWidth =mHolder.getCardPriceContainerView().getWidth();
            int cardPriceContainerHeight = mHolder.getCardPriceContainerView().getHeight();
            mHolder.getCardPriceContainerView().setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, cardPriceContainerHeight));
        }

    }

    @Override
    public int
    getItemCount() {
        return mDataSet.size();
    }


    @Override
    public void onScaleAction(Matrix matrix) {
        Log.d("Monica", "ProductListAdapter - onScaleAction called");
        mMatrix = matrix;
        notifyDataSetChanged();
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
