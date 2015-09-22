package com.example.dskrundz.buzzerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;

/*
Manages the state of the multiplayer activity
 */
public class MultiplayerGameActivity extends AppCompatActivity {
	private ImageView player1ImageView = null;
	private ImageView player2ImageView = null;
	private ImageView player3ImageView = null;
	private ImageView player4ImageView = null;

	private int playerCount = 0;
	private List<Integer> playersHit = new ArrayList<Integer>();

	private boolean gameRunning = false;
	private Handler timingHandler = new Handler();
	private Runnable expireGameRunnable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_multiplayer_game);

		this.player1ImageView = (ImageView) this.findViewById(R.id.player1Image);
		this.player2ImageView = (ImageView) this.findViewById(R.id.player2Image);
		this.player3ImageView = (ImageView) this.findViewById(R.id.player3Image);
		this.player4ImageView = (ImageView) this.findViewById(R.id.player4Image);

		Intent callingIntent = this.getIntent();
		this.playerCount = callingIntent.getIntExtra(this.getString(R.string.playerCount), 0);

		this.setTitle(String.format("%d %s", this.playerCount, this.getTitle().toString()));

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
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Point tapPoint = new Point((int) event.getX(), (int) event.getY());
			if (this.viewContainsPoint(this.player1ImageView, tapPoint)) {
				if (!this.playersHit.contains(0)) {
					this.startGame(0);
				}
			} else if (this.viewContainsPoint(this.player2ImageView, tapPoint)) {
				if (!this.playersHit.contains(1)) {
					this.startGame(1);
				}
			} else if (this.viewContainsPoint(this.player3ImageView, tapPoint)) {
				if (!this.playersHit.contains(2)) {
					this.startGame(2);
				}
			} else if (this.viewContainsPoint(this.player4ImageView, tapPoint)) {
				if (!this.playersHit.contains(3)) {
					this.startGame(3);
				}
			}

			if (this.playersHit.size() == 1) {
				this.timingHandler.postDelayed(this.expireGameRunnable, 1000);
			} else if (this.playersHit.size() == this.playerCount) {
				this.endGame();
			}
			return true;
		}
		return false;
	}

	private void startGame(Integer playerNumber) {
		this.playersHit.add(playerNumber);

		DataCenter.sharedDataCenter().multiplayerBuzzes[this.playerCount-2][playerNumber] += 1;

		if (!this.gameRunning) {
			this.gameRunning = true;

			this.timingHandler.postDelayed(this.expireGameRunnable, 1000);
		}
	}

	private static final String[] names = {"Canada", "Britain", "France", "Alberta"};

	private void endGame() {
		this.timingHandler.removeCallbacksAndMessages(null);
		this.gameRunning = false;

		DataCenter.sharedDataCenter().multiplayerWins[this.playerCount-2][this.playersHit.get(0)] += 1;
		DataCenter.sharedDataCenter().save(this);

		String alertTitle = "1. " + MultiplayerGameActivity.names[this.playersHit.remove(0)];
		String alertMessage = "";
		Integer place = 2;
		while (this.playersHit.size() > 0) {
			alertMessage += (place++).toString() + ". " + MultiplayerGameActivity.names[this.playersHit.remove(0)] + "\n";
		}

		// http://stackoverflow.com/a/2115770/393009 without the action
		new AlertDialog.Builder(this)
				.setTitle(alertTitle)
				.setMessage(alertMessage.substring(0, Math.max(0, alertMessage.length() - 1)))
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.show();
	}

	// Based on http://stackoverflow.com/a/5735817/393009 with a fix for the Rect that is built and a simpler check
	private boolean viewContainsPoint(View view, Point point) {
		int[] viewOrigin = new int[2];
		view.getLocationOnScreen(viewOrigin);
		Rect viewFrame = new Rect(viewOrigin[0], viewOrigin[1], viewOrigin[0]+view.getWidth(), viewOrigin[1]+view.getHeight());
		return viewFrame.contains(point.x, point.y);
	}
}