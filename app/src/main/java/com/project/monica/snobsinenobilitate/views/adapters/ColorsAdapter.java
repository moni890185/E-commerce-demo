package com.project.monica.snobsinenobilitate.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.widget.Button;
import com.project.monica.snobsinenobilitate.models.pojo.Color;
import com.project.monica.snobsinenobilitate.utils.Logger;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.util.List;

/**
 * Created by monica on 06/03/2015.
 */
public class ColorsAdapter extends ProductFeaturesAdapter {

  public ColorsAdapter(Context context, List<Color> colors) {
    super(context, colors);
  }

  @Override protected void setButtonFeature(final int position, final Button mButton) {
    {
      Logger.d("color" + getDataset().get(position).getName());
      Target target = new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
          Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
              Logger.d("dark muted "
                  + palette.getDarkMutedSwatch()
                  + " palette: "
                  + palette.getDarkVibrantSwatch()
                  + " light vibrant: "
                  + palette.getLightVibrantSwatch());
              Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
              if (vibrantSwatch != null) {

                mButton.getBackground()
                    .setColorFilter(vibrantSwatch.getRgb(), PorterDuff.Mode.MULTIPLY);
              } else {
                mButton.setText(getDataset().get(position).getName());
              }
            }
          });
        }

        @Override public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
      };
      Picasso.with(mContext)
          .load(getDataset().get(position).getSwatchUrl())
          .into(target);
    }
  }

  @Override List<Color> getDataset() {
    return mDataset;
  }
}
