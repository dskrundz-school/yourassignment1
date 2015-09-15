package ca.skrundz.buzzerapp;

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

	protected DataCenter() {
		// TODO: Load
	}

	public void save() {
		// TODO: Save
	}
}