package com.capstone.Lexington;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
    private Context context;
    private Integer[] imageIDs;
    
    // constructor
    public ImageAdapter(Context c, Integer[] array)
    {
        context = c;
        imageIDs = array;
    }
    
    public int getCount()
    {
        return imageIDs.length;
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
			imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5, 5, 5, 5);
			
			convertView = imageView;
			
		} else {
			
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(imageIDs[position]);
		return imageView;
	}
}