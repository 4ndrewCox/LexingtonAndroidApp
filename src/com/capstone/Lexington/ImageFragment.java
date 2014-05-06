package com.capstone.Lexington;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrew on 5/5/2014.
 */
public class ImageFragment extends Fragment {
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate view
        return i.inflate(R.layout.fragment_images, container, false);
    }
}
