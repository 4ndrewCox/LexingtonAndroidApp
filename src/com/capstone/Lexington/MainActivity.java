package com.capstone.Lexington;

import java.io.IOException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener{

    //Declare necessary class members
    private ViewPager mPager; //Changes the views with swipes
    private TabPagerAdapter mAdapter;//enables tabs in action bar
    private ActionBar mActionBar;

    //titles for the tabs
    private String[] tabs = {"Text", "Audio", "Video", "Images"};

    //stuff that needs to be done when app starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//set the view to the main activity
        TextView previewDescription = (TextView)findViewById(R.id.previewDesc);

        //get the pager, action bar and adapters
        mPager = (ViewPager) findViewById(R.id.pager); // pulls from activity_main
        mActionBar = getActionBar();
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        
       String scanResult = new String(); 
       Intent scanner = getIntent();
       try{scanResult = scanner.getStringExtra("result");}
       catch(Exception e)
       {
    	   Toast.makeText(getApplicationContext(), "no result", Toast.LENGTH_LONG).show();
       }
       
       DatabaseHelper myDB = new DatabaseHelper(this, scanResult);
       try{myDB.createDataBase();}
       catch(IOException e)
       {
    	   throw new Error("Unable to create database!");
       }
       try{myDB.openDataBase();}
       catch(SQLException e)
       {
    	   throw e;
       }
       
       Exhibit currentExhibit = new Exhibit(myDB.getExhibit());
       
       previewDescription.setText(currentExhibit.name);
       
        //set the adapter to the pager
        mPager.setAdapter(mAdapter);

        //enable tab navigation
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getActionBar().setDisplayShowHomeEnabled(false);

        //populate the tabs with titles
        for(String tabName : tabs)
        {
            mActionBar.addTab(mActionBar.newTab().setText(tabName)
                                       .setTabListener(this));
        }

        //tab's listener for tab changes
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                mActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg1, float arg2, int arg3)
            {

            }

            @Override
            public void onPageScrollStateChanged(int arg)
            {

            }
        });

    }


    //things that need to be done before menu is created
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.actionbar_actions, menu);

        //make sure the scanner and location are shown on the action
        // bar
        MenuItem scanner = menu.findItem(R.id.action_scan);
        MenuItem location = menu.findItem(R.id.action_loc);
        MenuItemCompat.setShowAsAction(scanner, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setShowAsAction(location,MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

        //return superclass
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	 
        int id = item.getItemId();
        
        switch(id)
        {
            case R.id.action_scan:
                //Toast.makeText(this, "Scan", Toast.LENGTH_SHORT).show();
               Intent openScan = new Intent(this, ScanActivity.class);
               startActivity(openScan);
                break;
            case R.id.action_loc:
                //Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();
            	 Intent openMap = new Intent(this, MapActivity.class);
                 startActivity(openMap);
                break;
            case R.id.action_help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;

        }return false;
    }

    //Listeners for the tab selection
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {
        mPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft)
    {

    }

   @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft)
    {

    }

}
