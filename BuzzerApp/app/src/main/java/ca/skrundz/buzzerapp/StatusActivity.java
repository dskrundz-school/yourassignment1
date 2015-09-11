package ca.skrundz.buzzerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
Manages the UI for displaying the stats
 */
public class StatusActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_status);
	}
}