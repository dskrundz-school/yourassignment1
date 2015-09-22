package ca.skrundz.buzzerapp;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
Holds all of the past data and stuff
 */
public class DataCenter {
	private static DataCenter instance = null;

	public List<Double> singlePlayerTimes = new ArrayList<Double>();

	public int[][] multiplayerBuzzes = {
			                                   {0, 0},
			                                   {0, 0, 0},
			                                   {0, 0, 0, 0}

	};

	public int[][] multiplayerWins = {
			                                 {0, 0},
			                                 {0, 0, 0},
			                                 {0, 0, 0, 0}

	};

	public static DataCenter sharedDataCenter() {
		if (instance == null) {
			instance = new DataCenter();
		}
		return instance;
	}

	private static final String FileName = "Stats.bin";

	private DataCenter() {
	}

	public void reset(Context context) {
		context.deleteFile(FileName);
		instance = new DataCenter();
	}

	public void load(Context context) {
		try {
			FileInputStream inputStream = context.openFileInput(FileName);
			ObjectInputStream in = new ObjectInputStream(inputStream);
			this.singlePlayerTimes = (List<Double>) in.readObject();
			this.multiplayerBuzzes = (int[][]) in.readObject();
			this.multiplayerWins = (int[][]) in.readObject();
		} catch (Exception e) {}
	}

	public void save(Context context) {
		try {
			FileOutputStream outputStream = context.openFileOutput(FileName, context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(outputStream);
			out.writeObject(this.singlePlayerTimes);
			out.writeObject(this.multiplayerBuzzes);
			out.writeObject(this.multiplayerWins);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// Do nothing
			Log.e("", e.getStackTrace().toString());
			throw new RuntimeException(e);
		} catch (IOException e) {
			// Do nothing
			Log.e("", e.getStackTrace().toString());
			throw new RuntimeException(e);
		}
	}
}