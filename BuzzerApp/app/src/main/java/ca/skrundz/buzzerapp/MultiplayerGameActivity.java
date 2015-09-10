package ca.skrundz.buzzerapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class MultiplayerGameActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_multiplayer_game);

		Intent callingIntent = this.getIntent();
		int playerCount = callingIntent.getIntExtra(this.getString(R.string.playerCount), 0);

		switch (playerCount) {
			case 2:
				this.findViewById(R.id.player3Image).setVisibility(View.GONE);
			case 3:
				this.findViewById(R.id.player4Image).setVisibility(View.GONE);
		}

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int w = size.x;
		int h = size.y;

		Log.i("", String.format("Screen: %d %d", w, h));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();

			Log.i("", String.format("touch: %.2f %.2f", x, y));

			return true;
		}
		return false;
	}
}