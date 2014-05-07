package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity
{
  private int TIME_OUT = 3000;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_splash);
    getActionBar().setDisplayShowHomeEnabled(false);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(SplashScreen.this, TutorialActivity.class);
        localIntent.putExtra("id", 0);
        SplashScreen.this.startActivity(localIntent);
      }
    }
    , this.TIME_OUT);
  }
}