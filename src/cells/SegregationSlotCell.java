package cells;

import java.util.List;

import javafx.scene.paint.Color;

public class SegregationSlotCell extends Cell {
	private int state;
	private double tPercentage;
	private final int OTHER_STATE;

	public SegregationSlotCell(Color c, double t, int s, int o) {
		super(c);
		setState(s);
		tPercentage = t;
		OTHER_STATE = o;
		// TODO Auto-generated constructor stub
	}

	public boolean isUnsatisfied(List<Integer> neighborStates) {
		if (getState() == 0) {
			return false;
		} else {
			double numSimilar = getNumOfState(neighborStates, getState());
			double numNotSimilar = getNumOfState(neighborStates, OTHER_STATE);
			return ((numSimilar / (numNotSimilar + numSimilar)) < tPercentage);
		}

	}

	private double getNumOfState(List<Integer> neighborStates, int stateToCheck) {
		double count = 0.0;
		for (int i : neighborStates) {
			if (i == stateToCheck)
				count++;
		}

		return count;
	}

	public int getState() {
		return state;
	}

	private void setState(int newState) {
		state = newState;
	}

}
