package com.capstone.Lexington;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Andrew on 5/5/2014.
 */

public class ImageFragment extends Fragment {
	
	Integer[] imageIDs = {R.drawable.a4_skyhawk, R.drawable.skyhawk1, R.drawable.skyhawk2, R.drawable.skyhawk3};
	
	GridView gridView;
	
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
    	
    	ViewGroup root = (ViewGroup) i.inflate(R.layout.fragment_images, container, false);
    	
    	 gridView = (GridView) root.findViewById(R.id.gridView);
         
         gridView.setAdapter(new ImageAdapter(getActivity(), imageIDs));
         
         gridView.setOnItemClickListener(new OnItemClickListener()
         {
             public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                 {
            	 	Intent indivImage = new Intent(getActivity(), IndivImageActivity.class);
            	 	indivImage.putExtra("imageID", imageIDs[position]);
            	 	indivImage.putExtra("imageText", "test text");
            	 	
            	 	startActivity(indivImage);
                    //Toast.makeText(getActivity(), "pic" + (position + 1) + "selected",Toast.LENGTH_SHORT).show();
                 }
         });
    	
    	//inflate view
        return root;
    }
    
}
