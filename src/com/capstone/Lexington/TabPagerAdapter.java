package com.capstone.Lexington;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Raymond on 3/8/14.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public Fragment getItem(int index)
    {
        //use the tab switcher to select their respective fragments
        switch(index)
        {
            case 0:
                return new InfoFragment();
            case 1:
                return new AudioFragment();
            case 2:
                return new VideoFragment();
            case 3:
                return new ImageFragment();
        }

        return null;

    }

    public int getCount()
    {//sets the size of the tab list
        return 4;
    }
}
