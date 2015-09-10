package ca.skrundz.buzzerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MultiplayerActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_multiplayer);
	}

	public void on2Players(View view) {
		Intent intent = new Intent(this, MultiplayerGameActivity.class);
		intent.putExtra(this.getString(R.string.playerCount), 2);
		this.startActivity(intent);
	}

	public void on3Players(View view) {
		Intent intent = new Intent(this, MultiplayerGameActivity.class);
		intent.putExtra(this.getString(R.string.playerCount), 3);
		this.startActivity(intent);
	}

	public void on4Players(View view) {
		Intent intent = new Intent(this, MultiplayerGameActivity.class);
		intent.putExtra(this.getString(R.string.playerCount), 4);
		this.startActivity(intent);
	}
}