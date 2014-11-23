package com.droidcoder.expandviewexample.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeViewHeightAnimation extends Animation {

  private int mHeight;
  private int mStartHeight;
  private View mView;

  public ResizeViewHeightAnimation(View view, int width) {
    mView = view;
    mHeight = width;
    mStartHeight = view.getHeight();
  }

  @Override
  protected void applyTransformation(float interpolatedTime, Transformation t) {
    int newWidth = mStartHeight + (int) ((mHeight - mStartHeight) * interpolatedTime);

    mView.getLayoutParams().height = newWidth;
    mView.requestLayout();
  }

  @Override
  public void initialize(int width, int height, int parentWidth, int parentHeight) {
    super.initialize(width, height, parentWidth, parentHeight);
  }

  @Override
  public boolean willChangeBounds() {
    return true;
  }
}