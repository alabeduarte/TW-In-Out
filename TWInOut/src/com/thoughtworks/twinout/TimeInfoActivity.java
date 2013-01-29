package com.thoughtworks.twinout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.thoughtworks.twinout.db.TimeCardDataSource;

public class TimeInfoActivity extends Activity implements OnClickListener {

	public static final String REGISTERED = "REGISTERED"; 
	private TimeCardDataSource dataSource;
	
	@Override public void onClick(View view) {
		InOutRegister register = new InOutRegister(dataSource);
		
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		
		register.registerInAt(ViewHelper.getInputDate(datePicker, timePicker));
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(REGISTERED, TimeCardType.IN);
		startActivity(intent);		
	}
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_info); 
		
		dataSource = new TimeCardDataSource(this);
		dataSource.open();
		
		Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
		buttonConfirm.setOnClickListener(this);
	}

	@Override protected void onResume() {
		dataSource.open();
		super.onResume();
	}
	
	@Override protected void onPause() {
		dataSource.close();
		super.onPause();
	}
}
