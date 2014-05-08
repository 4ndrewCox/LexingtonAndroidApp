package com.capstone.Lexington;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Bitmap> imageIDs;
    
    // constructor
    public ImageAdapter(Context c, ArrayList<Bitmap> scaledImages)
    {
        context = c;
        imageIDs = scaledImages;
    }
    
    public int getCount()
    {
        return imageIDs.size();
    }
    
    public Object getItem(int position) 
    {
        return position;
    }
    
    public long getItemId(int position)
    {
        return position;
    }

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView;	
		
		if (convertView == null) {
		
			imageView = new ImageView(context);
			imageView.setPadding(5, 5, 5, 5);
			
			convertView = imageView;
			
		} else {
			
			imageView = (ImageView) convertView;
		}

		imageView.setImageBitmap(imageIDs.get(position));
		return imageView;
	}
}