package com.capstone.Lexington;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Raymond on 3/8/14.
 */
public class AudioFragment extends Fragment {
    public View onCreateView(LayoutInflater i, ViewGroup container,
                                         Bundle savedInstanceState)
    {
        //inflate view
        return i.inflate(R.layout.fragment_audio, container, false);
    }
}
