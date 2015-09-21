package ca.skrundz.buzzerapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Manages the UI for displaying the stats
 */
public class StatusActivity extends AppCompatActivity {
	private List<Double> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_status);

		DataCenter data = DataCenter.sharedDataCenter();

		// Single Player Stats
		TextView last10MinView = (TextView)this.findViewById(R.id.MinimumLast10);
		TextView last100MinView = (TextView)this.findViewById(R.id.MinimumLast100);
		TextView allTimeMinView = (TextView)this.findViewById(R.id.MinimumAllTime);

		TextView last10MaxView = (TextView)this.findViewById(R.id.MaximumLast10);
		TextView last100MaxView = (TextView)this.findViewById(R.id.MaximumLast100);
		TextView allTimeMaxView = (TextView)this.findViewById(R.id.MaximumAllTime);

		TextView last10AvgView = (TextView)this.findViewById(R.id.AverageLast10);
		TextView last100AvgView = (TextView)this.findViewById(R.id.AverageLast100);
		TextView allTimeAvgView = (TextView)this.findViewById(R.id.AverageAllTime);

		TextView last10MedView = (TextView)this.findViewById(R.id.MedianLast10);
		TextView last100MedView = (TextView)this.findViewById(R.id.MedianLast100);
		TextView allTimeMedView = (TextView)this.findViewById(R.id.MedianAllTime);

		this.setString(String.format("%.2f", this.min(this.last10(data.singlePlayerTimes))), last10MinView);
		this.setString(String.format("%.2f", this.min(this.last100(data.singlePlayerTimes))), last100MinView);
		this.setString(String.format("%.2f", this.min(data.singlePlayerTimes)), allTimeMinView);

		this.setString(String.format("%.2f", this.max(this.last10(data.singlePlayerTimes))), last10MaxView);
		this.setString(String.format("%.2f", this.max(this.last100(data.singlePlayerTimes))), last100MaxView);
		this.setString(String.format("%.2f", this.max(data.singlePlayerTimes)), allTimeMaxView);

		this.setString(String.format("%.2f", this.average(this.last10(data.singlePlayerTimes))), last10AvgView);
		this.setString(String.format("%.2f", this.average(this.last100(data.singlePlayerTimes))), last100AvgView);
		this.setString(String.format("%.2f", this.average(data.singlePlayerTimes)), allTimeAvgView);

		this.setString(String.format("%.2f", this.median(this.last10(data.singlePlayerTimes))), last10MedView);
		this.setString(String.format("%.2f", this.median(this.last100(data.singlePlayerTimes))), last100MedView);
		this.setString(String.format("%.2f", this.median(data.singlePlayerTimes)), allTimeMedView);

		// Multiplayer stats
		TextView m2p1b = (TextView)this.findViewById(R.id.m2p1b);
		TextView m2p2b = (TextView)this.findViewById(R.id.m2p2b);
		TextView m2p1w = (TextView)this.findViewById(R.id.m2p1w);
		TextView m2p2w = (TextView)this.findViewById(R.id.m2p2w);

		TextView m3p1b = (TextView)this.findViewById(R.id.m3p1b);
		TextView m3p2b = (TextView)this.findViewById(R.id.m3p2b);
		TextView m3p3b = (TextView)this.findViewById(R.id.m3p3b);
		TextView m3p1w = (TextView)this.findViewById(R.id.m3p1w);
		TextView m3p2w = (TextView)this.findViewById(R.id.m3p2w);
		TextView m3p3w = (TextView)this.findViewById(R.id.m3p3w);

		TextView m4p1b = (TextView)this.findViewById(R.id.m4p1b);
		TextView m4p2b = (TextView)this.findViewById(R.id.m4p2b);
		TextView m4p3b = (TextView)this.findViewById(R.id.m4p3b);
		TextView m4p4b = (TextView)this.findViewById(R.id.m4p4b);
		TextView m4p1w = (TextView)this.findViewById(R.id.m4p1w);
		TextView m4p2w = (TextView)this.findViewById(R.id.m4p2w);
		TextView m4p3w = (TextView)this.findViewById(R.id.m4p3w);
		TextView m4p4w = (TextView)this.findViewById(R.id.m4p4w);

		this.setInt(data.multiplayerBuzzes[0][0], m2p1b);
		this.setInt(data.multiplayerBuzzes[0][1], m2p2b);

		this.setInt(data.multiplayerBuzzes[1][0], m3p1b);
		this.setInt(data.multiplayerBuzzes[1][1], m3p2b);
		this.setInt(data.multiplayerBuzzes[1][2], m3p3b);

		this.setInt(data.multiplayerBuzzes[2][0], m4p1b);
		this.setInt(data.multiplayerBuzzes[2][1], m4p2b);
		this.setInt(data.multiplayerBuzzes[2][2], m4p3b);
		this.setInt(data.multiplayerBuzzes[2][3], m4p4b);

		this.setInt(data.multiplayerWins[0][0], m2p1w);
		this.setInt(data.multiplayerWins[0][1], m2p2w);

		this.setInt(data.multiplayerWins[1][0], m3p1w);
		this.setInt(data.multiplayerWins[1][1], m3p2w);
		this.setInt(data.multiplayerWins[1][2], m3p3w);

		this.setInt(data.multiplayerWins[2][0], m4p1w);
		this.setInt(data.multiplayerWins[2][1], m4p2w);
		this.setInt(data.multiplayerWins[2][2], m4p3w);
		this.setInt(data.multiplayerWins[2][3], m4p4w);
	}

	private void setInt(int i, TextView textView) {
		this.setString(String.format("%d", i), textView);
	}

	private void setString(String string, TextView textView) {
		textView.setText(string.toCharArray(), 0, string.length());
	}

	private List<Double> last10(List<Double> list) {
		List<Double> newList = new ArrayList<>();
		for (int i = 0; i < Math.min(10, list.size()); ++i) {
			newList.add(list.get(list.size() - 1 - i));
		}
		return newList;
	}

	private List<Double> last100(List<Double> list) {
		List<Double> newList = new ArrayList<>();
		for (int i = 0; i < Math.min(100, list.size()); ++i) {
			newList.add(list.get(list.size() - 1 - i));
		}
		return newList;
	}

	private Double min(List<Double> list) {
		if (list.size() == 0) { return 0.0; }
		Double min = Double.MAX_VALUE;
		for (Double d : list) {
			if (min > d) {
				min = d;
			}
		}
		return min;
	}

	private Double max(List<Double> list) {
		Double max = 0.0;
		for (Double d : list) {
			if (max < d) {
				max = d;
			}
		}
		return max;
	}

	private Double average(List<Double> list) {
		if (list.size() == 0) {
			return 0.0;
		}
		Double sum = 0.0;
		for (Double d : list) {
			sum += d;
		}
		return sum / list.size();
	}

	private Double median(List<Double> list) {
		Collections.sort(list);
		if (list.size() % 2 == 1) {
			return list.get((int)Math.floor(((double) list.size()) / 2.0));
		} else {
			if (list.size() == 0) {
				return 0.0;
			}
			List<Double> newList = new ArrayList<>();
			newList.add(list.get(list.size() / 2));
			newList.add(list.get(list.size() / 2 - 1));
			return this.average(newList);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		this.getMenuInflater().inflate(R.menu.menu_status, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.clearStats) {
			DataCenter.sharedDataCenter().reset(this);
			Intent intent = new Intent(this, StatusActivity.class);
			this.startActivity(intent);
			this.finish();
		} else if (id == R.id.emailStats) {
			String statsText = "";

			statsText += "Minimum:\n";
			statsText += "\tLast 10: " + ((TextView)this.findViewById(R.id.MinimumLast10)).getText() + "\n";
			statsText += "\tLast 100: " + ((TextView)this.findViewById(R.id.MinimumLast100)).getText() + "\n";
			statsText += "\tAll Time: " + ((TextView)this.findViewById(R.id.MinimumAllTime)).getText() + "\n";
			statsText += "\n";

			statsText += "Maximum:\n";
			statsText += "\tLast 10: " + ((TextView)this.findViewById(R.id.MaximumLast10)).getText() + "\n";
			statsText += "\tLast 100: " + ((TextView)this.findViewById(R.id.MaximumLast100)).getText() + "\n";
			statsText += "\tAll Time: " + ((TextView)this.findViewById(R.id.MaximumAllTime)).getText() + "\n";
			statsText += "\n";

			statsText += "Average:\n";
			statsText += "\tLast 10: " + ((TextView)this.findViewById(R.id.AverageLast10)).getText() + "\n";
			statsText += "\tLast 100: " + ((TextView)this.findViewById(R.id.AverageLast100)).getText() + "\n";
			statsText += "\tAll Time: " + ((TextView)this.findViewById(R.id.AverageAllTime)).getText() + "\n";
			statsText += "\n";

			statsText += "Median:\n";
			statsText += "\tLast 10: " + ((TextView)this.findViewById(R.id.MedianLast10)).getText() + "\n";
			statsText += "\tLast 100: " + ((TextView)this.findViewById(R.id.MedianLast100)).getText() + "\n";
			statsText += "\tAll Time: " + ((TextView)this.findViewById(R.id.MedianAllTime)).getText() + "\n";
			statsText += "\n";

			statsText += "\n";
			statsText += "\n";

			statsText += "2 Player:\n";
			statsText += "\tPlayer 1 Buzzes: " + ((TextView)this.findViewById(R.id.m2p1b)).getText() + "\n";
			statsText += "\tPlayer 1 Wins: " + ((TextView)this.findViewById(R.id.m2p1w)).getText() + "\n";
			statsText += "\tPlayer 2 Buzzes: " + ((TextView)this.findViewById(R.id.m2p2b)).getText() + "\n";
			statsText += "\tPlayer 2 Wins: " + ((TextView)this.findViewById(R.id.m2p2w)).getText() + "\n";
			statsText += "\n";

			statsText += "2 Player:\n";
			statsText += "\tPlayer 1 Buzzes: " + ((TextView)this.findViewById(R.id.m3p1b)).getText() + "\n";
			statsText += "\tPlayer 1 Wins: " + ((TextView)this.findViewById(R.id.m3p1w)).getText() + "\n";
			statsText += "\tPlayer 2 Buzzes: " + ((TextView)this.findViewById(R.id.m3p2b)).getText() + "\n";
			statsText += "\tPlayer 2 Wins: " + ((TextView)this.findViewById(R.id.m3p2w)).getText() + "\n";
			statsText += "\tPlayer 3 Buzzes: " + ((TextView)this.findViewById(R.id.m3p3b)).getText() + "\n";
			statsText += "\tPlayer 3 Wins: " + ((TextView)this.findViewById(R.id.m3p3w)).getText() + "\n";
			statsText += "\n";

			statsText += "2 Player:\n";
			statsText += "\tPlayer 1 Buzzes: " + ((TextView)this.findViewById(R.id.m4p1b)).getText() + "\n";
			statsText += "\tPlayer 1 Wins: " + ((TextView)this.findViewById(R.id.m4p1w)).getText() + "\n";
			statsText += "\tPlayer 2 Buzzes: " + ((TextView)this.findViewById(R.id.m4p2b)).getText() + "\n";
			statsText += "\tPlayer 2 Wins: " + ((TextView)this.findViewById(R.id.m4p2w)).getText() + "\n";
			statsText += "\tPlayer 3 Buzzes: " + ((TextView)this.findViewById(R.id.m4p3b)).getText() + "\n";
			statsText += "\tPlayer 3 Wins: " + ((TextView)this.findViewById(R.id.m4p3w)).getText() + "\n";
			statsText += "\tPlayer 4 Buzzes: " + ((TextView)this.findViewById(R.id.m4p4b)).getText() + "\n";
			statsText += "\tPlayer 4 Wins: " + ((TextView)this.findViewById(R.id.m4p4w)).getText() + "\n";

			// http://stackoverflow.com/a/2197841/393009
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("message/rfc822");
			intent.putExtra(Intent.EXTRA_EMAIL, "");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Reflex Stats");
			intent.putExtra(Intent.EXTRA_TEXT, statsText);
			try {
				this.startActivity(Intent.createChooser(intent, "Send mail..."));
			} catch (ActivityNotFoundException e) {
				Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		}

		return super.onOptionsItemSelected(item);
	}
}