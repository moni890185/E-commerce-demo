package com.project.monica.openreplyproject.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import timber.log.Timber;

/**
 * Created by monica on 17/12/2014.
 */
public class ProductListAdapter extends RecyclerView.Adapter implements ScaleGestureListener.ScalePinchListener {

    public interface OnCustomClickListener
    {
        void onClick(Integer productId, View view);
    }

    // Fields
    private ArrayList<Product> mDataSet;
    private Matrix mMatrix = null;
    private Context mContext;
    private OnCustomClickListener mClickListener;

    // Constructor
    public ProductListAdapter(Context context, ArrayList<Product> dataset, OnCustomClickListener listener) {
        mDataSet = dataset;
        mContext = context;
        mClickListener = listener;
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

        Timber.d("onBindViewHolder - price: " + product.getmPrice());

        String price = product.getmPrice();
        mHolder.getCardPriceView().setText(TextUtils.concat("£ ", price));

        Timber.d("onBindViewHolder - img_id: " + product.getImageDrawable());
        int imgId = product.getImageDrawable();
        mHolder.getCardImageView().setImageResource(imgId);

        mHolder.getCardView().setTag(product.getId());
        mHolder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer productId = (Integer)v.getTag();
                mClickListener.onClick(productId, v);
            }
        });

        mHolder.getCardTitleView().setText(product.getTitle());


        if (mMatrix != null) {
            Timber.d("NOT USING MATRIX - but resizing");
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
        Timber.d("ProductListAdapter - onScaleAction called");
        mMatrix = matrix;
        notifyDataSetChanged();
    }


    // ViewHolder
    private static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView mCardView;
        private final ImageView mCardImageView;
        private final TextView mCardPriceView;
        private final LinearLayout mCardPriceLayout;
        private final TextView mCardTitleView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mCardImageView = (ImageView) itemView.findViewById(R.id.img_card_view);
            mCardPriceView = (TextView) itemView.findViewById(R.id.card_price_text);
            mCardPriceLayout = (LinearLayout) itemView.findViewById(R.id.card_price_container);
            mCardTitleView = (TextView)itemView.findViewById(R.id.card_title_text);
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

        public TextView getCardTitleView()
        {
            return mCardTitleView;
        }
    }

}
