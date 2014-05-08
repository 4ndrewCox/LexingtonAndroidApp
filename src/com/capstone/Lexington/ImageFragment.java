package com.capstone.Lexington;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * Created by Andrew on 5/5/2014.
 */

public class ImageFragment extends Fragment {

	Integer[] imageIDs = { R.drawable.a4_skyhawk, R.drawable.skyhawk1,
			R.drawable.skyhawk2, R.drawable.skyhawk3 };

	GridView gridView;

	public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) i.inflate(R.layout.fragment_images, container, false);

		gridView = (GridView) root.findViewById(R.id.gridView);

		popGallery task = new popGallery();
		task.execute(imageIDs);

		// inflate view
		return root;
	}

	public class popGallery extends AsyncTask<Integer[], Void, Void> {
    	ArrayList<Bitmap> scaledImages = new ArrayList<Bitmap>();
    	
		@Override
		protected Void doInBackground(Integer[]... params) {

			Integer[] images = params[0];
			
			for(int i=0; i < images.length; i++)
			{
				Bitmap image = BitmapFactory.decodeResource(getResources(), images[i]);
				Bitmap newImage = Bitmap.createScaledBitmap(image, 300, 300, false);
				scaledImages.add(newImage);
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			gridView.setAdapter(new ImageAdapter(getActivity(), scaledImages));

			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					Intent indivImage = new Intent(getActivity(),
							IndivImageActivity.class);
					indivImage.putExtra("imageID", imageIDs[position]);
					indivImage.putExtra("imageText", "test text");

					startActivity(indivImage);
				}
			});

			super.onPostExecute(result);
		}

    } // end asynctask
}
