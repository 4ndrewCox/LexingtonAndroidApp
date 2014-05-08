package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VideoRowActivity extends Activity {

	TextView videoName;
	TextView videoDesc;
	/** something for video **/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_video_row);
		
		videoName = (TextView) findViewById(R.id.videoName);
		videoDesc = (TextView) findViewById(R.id.videoDesc);
		/** something for video **/
		
		
		Intent info = getIntent();
		videoName.setText(info.getStringExtra("name"));
		videoDesc.setText(info.getStringExtra("desc"));
		/** something for video **/
	}
}
