package com.project.monica.snobsinenobilitate.views.adapters;

import android.content.Context;
import android.widget.Button;
import com.project.monica.snobsinenobilitate.models.pojo.Size;
import com.project.monica.snobsinenobilitate.utils.Utils;
import java.util.List;

/**
 * Created by monica on 06/03/2015.
 */
public class SizeAdapter extends ProductFeaturesAdapter {
  public SizeAdapter(Context context, List<Size> dataset) {
    super(context, dataset);
  }

  @Override List<Size> getDataset() {
    return mDataset;
  }

  @Override protected void setButtonFeature(int position, Button mButton) {
    String sizeName = Utils.sanitizeSize(getDataset().get(position).getName());
    mButton.setText(sizeName);

  }
}
