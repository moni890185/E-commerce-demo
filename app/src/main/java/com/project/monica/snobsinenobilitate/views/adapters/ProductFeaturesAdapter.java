package com.project.monica.snobsinenobilitate.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.project.monica.snobsinenobilitate.R;
import com.project.monica.snobsinenobilitate.utils.Logger;
import java.util.List;

/**
 * Created by monica on 04/03/2015.
 */
public abstract class ProductFeaturesAdapter<T extends Object> extends RecyclerView.Adapter {

  Context mContext;
  List<T> mDataset;

  abstract List<T> getDataset();

  public ProductFeaturesAdapter(Context context, List<T> dataset) {
    mContext = context;
    mDataset = dataset;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.product_detail_button, parent, false);
    return new ColorsViewHolder(v);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ColorsViewHolder mColorHolder = (ColorsViewHolder) holder;
    Button mButton = mColorHolder.getItem();
    Logger.d("setting color - color size" + mDataset);
    setButtonFeature(position, mButton);
  }

  protected abstract void setButtonFeature(int position,final Button mButton);

  @Override public int getItemCount() {
    return mDataset.size();
  }

  private static class ColorsViewHolder extends RecyclerView.ViewHolder {
    private final Button mButton;

    public ColorsViewHolder(View itemView) {
      super(itemView);
      mButton = (Button) itemView.findViewById(R.id.product_detail_dynamic_button_id);
    }

    public Button getItem() {
      return mButton;
    }
  }
}
