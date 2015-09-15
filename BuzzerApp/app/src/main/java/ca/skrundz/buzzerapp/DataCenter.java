package ca.skrundz.buzzerapp;

import android.content.Context;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/*
Holds all of the past data and stuff
 */
public class DataCenter {
	private static DataCenter instance = null;

	public List<Double> singlePlayerTimes = new ArrayList<>();

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
		if(instance == null) {
			instance = new DataCenter();
		}
		return instance;
	}

	private static final String FileName = "Stats.bin";

	protected DataCenter() {}

	public void load(Context context) {
		// TODO: Load
	}

	public void save(Context context) {
		// TODO: Save
	}
}