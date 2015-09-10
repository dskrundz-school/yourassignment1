package ca.skrundz.buzzerapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SinglePlayerActivity extends AppCompatActivity {
	Handler timingHandler = new Handler();
	private Runnable startGameRunnable = null;
	private Runnable gameTriggerRunnable = null;

	private GameManager game = null;

	private Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_single_player);

		this.game = new GameManager(
				                           (ImageView)this.findViewById(R.id.androidImageView),
				                           (TextView)this.findViewById(R.id.waitForRed),
				                           (TextView)this.findViewById(R.id.tapGreenText),
				                           (TextView)this.findViewById(R.id.scoreTextView),
				                           (Button)this.findViewById(R.id.restartButton)
		);

		this.startGameRunnable = new Runnable() {
			@Override
			public void run() {
				game.preStartGame();
				timingHandler.postDelayed(gameTriggerRunnable, 10 + random.nextInt(1900));
			}
		};

		this.gameTriggerRunnable = new Runnable() {
			@Override
			public void run() {
				game.startGame();
			}
		};

		this.onRestartAlone(null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!this.game.stopGame()) {
				this.timingHandler.removeCallbacksAndMessages(null);
			}
			return true;
		}
		return false;
	}

	public void onRestartAlone(View view) {
		this.game.prepareGame();
		this.timingHandler.postDelayed(this.startGameRunnable, 500);
	}
}