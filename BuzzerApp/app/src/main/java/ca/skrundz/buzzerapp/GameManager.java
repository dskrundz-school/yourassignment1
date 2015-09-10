package ca.skrundz.buzzerapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
	private ImageView androidImage;
	private TextView waitForRed;
	private TextView pressGreen;
	private TextView resultView;
	private Button restartButton;

	public GameManager(
			                  ImageView androidImage,
			                  TextView waitForRed,
			                  TextView pressGreen,
			                  TextView resultView,
			                  Button restartButton
	) {
		this.androidImage = androidImage;
		this.waitForRed = waitForRed;
		this.pressGreen = pressGreen;
		this.resultView = resultView;
		this.restartButton = restartButton;
	}

	public void prepareGame() {
		this.androidImage.setVisibility(View.GONE);
		this.waitForRed.setVisibility(View.VISIBLE);
		this.pressGreen.setVisibility(View.GONE);
		this.resultView.setVisibility(View.GONE);
		this.restartButton.setVisibility(View.GONE);

		this.androidImage.setImageResource(R.drawable.android_red);
	}

	private long startTime = 0;
	private List<Double> pastTimes = new ArrayList<>();

	public void preStartGame() {
		this.androidImage.setVisibility(View.VISIBLE);
		this.waitForRed.setVisibility(View.GONE);
		this.pressGreen.setVisibility(View.VISIBLE);
		this.resultView.setVisibility(View.GONE);
		this.restartButton.setVisibility(View.GONE);

		this.androidImage.setImageResource(R.drawable.android_red);

		this.startTime = 0;
	}

	public void startGame() {
		this.androidImage.setVisibility(View.VISIBLE);
		this.waitForRed.setVisibility(View.GONE);
		this.pressGreen.setVisibility(View.VISIBLE);
		this.resultView.setVisibility(View.GONE);
		this.restartButton.setVisibility(View.GONE);

		this.androidImage.setImageResource(R.drawable.android_green);

		this.startTime = System.nanoTime();
	}

	public boolean stopGame() {
		this.androidImage.setVisibility(View.GONE);
		this.waitForRed.setVisibility(View.GONE);
		this.pressGreen.setVisibility(View.GONE);
		this.resultView.setVisibility(View.VISIBLE);
		this.restartButton.setVisibility(View.VISIBLE);

		if (this.startTime == 0) {
			String failString = "You tapped too soon!";
			this.resultView.setText(failString.toCharArray(), 0, failString.length());
			return false;
		}

		long deltaTimeNS = System.nanoTime() - this.startTime;
		Double deltaTimeS = deltaTimeNS / 1000000000.0;
		this.pastTimes.add(deltaTimeS);
		String resultString = String.format("You took %.2f seconds", deltaTimeS);
		this.resultView.setText(resultString.toCharArray(), 0, resultString.length());

		this.startTime = 0;

		return true;
	}
}