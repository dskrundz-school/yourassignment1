package ca.skrundz.buzzerapp;

import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MultiplayerGameActivity extends AppCompatActivity {
	private ImageView player1ImageView = null;
	private ImageView player2ImageView = null;
	private ImageView player3ImageView = null;
	private ImageView player4ImageView = null;

	private int playerCount = 0;
	private List<Integer> playersHit = new ArrayList<>();

	private boolean gameRunning = false;
	private Handler timingHandler = new Handler();
	private Runnable expireGameRunnable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_multiplayer_game);

		this.player1ImageView = (ImageView)this.findViewById(R.id.player1Image);
		this.player2ImageView = (ImageView)this.findViewById(R.id.player2Image);
		this.player3ImageView = (ImageView)this.findViewById(R.id.player3Image);
		this.player4ImageView = (ImageView)this.findViewById(R.id.player4Image);

		Intent callingIntent = this.getIntent();
		this.playerCount = callingIntent.getIntExtra(this.getString(R.string.playerCount), 0);

		switch (this.playerCount) {
			case 2:
				this.player3ImageView.setVisibility(View.GONE);
			case 3:
				this.player4ImageView.setVisibility(View.GONE);
		}

		this.expireGameRunnable = new Runnable() {
			@Override
			public void run() {
				endGame();
			}
		};

		this.startGame();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();

			// TODO: Get the frame of the buttons and hit test the touch location

			Log.i("", String.format("touch: %.2f %.2f", x, y));

			return true;
		}
		return false;
	}

	public void onRestartGame(View button) {
		this.startGame();
	}

	private void startGame() {
		this.gameRunning = true;
	}

	private void endGame() {
		this.gameRunning = false;
	}
}