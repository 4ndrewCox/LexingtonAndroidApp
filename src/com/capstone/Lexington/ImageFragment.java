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
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Andrew on 5/5/2014.
 */

public class ImageFragment extends Fragment {

	Integer[] imageIDs = { R.drawable.a4_skyhawk, R.drawable.skyhawk1,
			R.drawable.skyhawk2, R.drawable.skyhawk3 };

	GridView gridView;
	TextView textProgress;
	ProgressBar imageProgress;
	
	public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) i.inflate(R.layout.fragment_images, container, false);

		gridView = (GridView) root.findViewById(R.id.gridView);
		
		popGallery task = new popGallery(root);
		task.execute(imageIDs);

		// inflate view
		return root;
	}

	public class popGallery extends AsyncTask<Integer[], Integer, Void> {
    	ArrayList<Bitmap> scaledImages = new ArrayList<Bitmap>();
    	
    	ViewGroup context;
    	
    	public popGallery(ViewGroup root)
    	{
    		context = root;
    	}
    	
    	@Override
		protected void onPreExecute() {
    		
    		imageProgress = (ProgressBar) context.findViewById(R.id.imageProgressBar);
    		textProgress = (TextView) context.findViewById(R.id.progressText);
    		
    		imageProgress.setVisibility(View.VISIBLE);
    		textProgress.setVisibility(View.VISIBLE);
    		
			super.onPreExecute();
		}

    	protected void onProgressUpdate(Integer... progress) {    
    		
			imageProgress.setProgress(progress[0]);
		}
    	
		@Override
		protected Void doInBackground(Integer[]... params) {
			
			Integer[] images = params[0];
			int size = images.length;
			
			for(int i=0; i < size; i++)
			{	
				Bitmap image = BitmapFactory.decodeResource(getResources(), images[i]);
				Bitmap newImage = Bitmap.createScaledBitmap(image, 300, 300, false);
				scaledImages.add(newImage);
				
				publishProgress((int) ((i / (float) size) * 100));
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
			
			gridView.setAdapter(new ImageAdapter(getActivity(), scaledImages));
			
			imageProgress.setVisibility(View.GONE);
			textProgress.setVisibility(View.GONE);
			
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
		}

    } // end asynctask
}
