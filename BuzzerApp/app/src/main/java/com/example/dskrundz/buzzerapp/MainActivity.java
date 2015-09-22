package com.example.dskrundz.buzzerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
The main entry point into the app, allows for navigation to different screens
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		DataCenter.sharedDataCenter().load(this);
	}

	public void onPlayAlone(View button) {
		Intent intent = new Intent(this, SinglePlayerActivity.class);
		this.startActivity(intent);
	}

	public void onPlayWithFriends(View button) {
		Intent intent = new Intent(this, MultiplayerActivity.class);
		this.startActivity(intent);
	}

	public void onViewStats(View button) {
		Intent intent = new Intent(this, StatusActivity.class);
		this.startActivity(intent);
	}
}