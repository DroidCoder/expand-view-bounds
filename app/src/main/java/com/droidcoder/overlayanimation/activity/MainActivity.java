package com.droidcoder.overlayanimation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import com.droidcoder.overlayanimation.R;
import com.droidcoder.overlayanimation.animation.ResizeViewHeightAnimation;

public class MainActivity extends ActionBarActivity {

  private View secondaryScreen;
  private View profileButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    secondaryScreen = findViewById(R.id.secondary_layout);
    profileButton = findViewById(R.id.fab_button);
    profileButton.setOnClickListener(new onFabButtonClick());
  }

  private final class onFabButtonClick implements View.OnClickListener {

    @Override public void onClick(View v) {
      final int targetSize = getWindowManager().getDefaultDisplay().getHeight() / 4;
      if (secondaryScreen.getHeight() == 0) {
        hideKeyboard();
        SwitchSecondaryViewVisibility(targetSize, -90, 200);
      } else if (secondaryScreen.getHeight() == targetSize) {
        SwitchSecondaryViewVisibility(0, 90, 200);
      }
    }
  }

  private void SwitchSecondaryViewVisibility(int overlaySize, int rotation, int duration) {
    profileButton.animate().rotationBy(rotation).setDuration(duration).start();
    Animation animation = new ResizeViewHeightAnimation(secondaryScreen, overlaySize);
    animation.setDuration(duration);
    secondaryScreen.startAnimation(animation);
  }

  private void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager inputManager =
          (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.hideSoftInputFromWindow(view.getWindowToken(),
          InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }
}
