package com.capstone.Lexington;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TutorialActivity extends ActionBarActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_tutorial);
    getActionBar().setDisplayShowHomeEnabled(false);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(R.menu.actionbar_tutorialactions, paramMenu);
    return true;
	}

	public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
		switch (paramMenuItem.getItemId()) {
		case R.id.action_scan:
			// Toast.makeText(this, "Scan", Toast.LENGTH_SHORT).show();
			Intent openScan = new Intent(this, ScanActivity.class);
			startActivity(openScan);
			break;
		case R.id.action_loc:
			// Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();
			Intent openMap = new Intent(this, MapActivity.class);
			startActivity(openMap);
			break;
		} return false;
	}
}