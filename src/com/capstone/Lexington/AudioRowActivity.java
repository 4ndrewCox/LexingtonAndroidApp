package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AudioRowActivity extends Activity {

	TextView audioName;
	TextView audioDesc;
	/** something for audio **/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_audio_row);
		
		audioName = (TextView) findViewById(R.id.audioName);
		audioDesc = (TextView) findViewById(R.id.audioDesc);
		/** something for audio **/
		
		
		Intent info = getIntent();
		audioName.setText(info.getStringExtra("name"));
		audioDesc.setText(info.getStringExtra("desc"));
		/** something for audio **/
	}
}
