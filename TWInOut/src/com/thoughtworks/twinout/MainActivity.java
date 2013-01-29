package com.thoughtworks.twinout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override public void onClick(View v) {
		Intent intent = new Intent(this, TimeInfoActivity.class);
		startActivity(intent);
	}
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.buttonEntry);
		button.setOnClickListener(this);
		
		this.changeButtonState(button);
	}
	
	private void changeButtonState(Button button) {
		Bundle extras = getIntent().getExtras();
		Object value = extras != null? extras.get(TimeInfoActivity.REGISTERED) : null;
	    if (value != null && value.equals(TimeCardType.IN)) {
	    	button.setText(getResources().getText(R.string.label_button_out));
	    }
	}

}
