package ca.skrundz.buzzerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
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