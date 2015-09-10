package ca.skrundz.buzzerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.logging.Handler;

public class SinglePlayerActivity extends AppCompatActivity {

//	private Handler timingHandler = new Handler();
	private Runnable startGameRunnable = null;
	private Runnable gameTriggerRunnable = null;

	private GameManager game = null;

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

//		this.startGameRunnable = new Runnable() {
//			@Override
//			public void run() {
				// Show the stuff
//				waitForRed.setVisibility(View.GONE);
//				pressGreen.setVisibility(View.VISIBLE);
//				androidImage.setImageResource(R.drawable.android_red);
//				androidImage.setVisibility(View.GONE);
				// Prepare the next timer
//			}
//		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			this.androidImage.setImageResource(R.drawable.android_green);
			return true;
		}
		return false;
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_single_player, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	*/
}
