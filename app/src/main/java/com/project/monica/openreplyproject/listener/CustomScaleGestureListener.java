package com.project.monica.openreplyproject.listener;

import android.graphics.Matrix;
import android.view.ScaleGestureDetector;

/**
 * Created by monica on 08/01/2015.
 */
public class CustomScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    // Interface
    public interface ScalePinchListener {
        public void onScaleAction(Matrix matrix);
    }

    private ScalePinchListener mScalePinchAdapterListener;
    private ScalePinchListener mScalePinchActivityListener;
    private float scale = 1f;
    private Matrix matrix = new Matrix();


    public void setScalePinchListener(ScalePinchListener mScalePinchAdapterListener, ScalePinchListener mScalePinchActivityListener) {

        this.mScalePinchAdapterListener = mScalePinchAdapterListener;
        this.mScalePinchActivityListener = mScalePinchActivityListener;
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        scale *= detector.getScaleFactor();
        scale = Math.max(0.1f, Math.min(scale, 5.0f));
        matrix.setScale(scale, scale);
        mScalePinchActivityListener.onScaleAction(matrix);
        mScalePinchAdapterListener.onScaleAction(matrix);
        return true;
    }
}
