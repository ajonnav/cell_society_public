package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class SegregationSlotCell extends SegregationTypeSquareCell {

	public SegregationSlotCell( Color c,
			double t, int s, int o) {
		super(0.0, 0.0, c, 0.0, 0.0, t);
		setState(s);
		OTHER_STATE = o;
		// TODO Auto-generated constructor stub
	}

	public SegregationSlotCell(SegregationSquareCell v, double t, int type) {
		super(v, t, type);
		// TODO Auto-generated constructor stub
	}
	
	//public boolean cellUnsatisfied(ListSegregationSlotCell)
	
	private double getNumOfState(ArrayList<Integer> neighborStates, int stateToCheck) {
		double count = 0.0;
		for (int i : neighborStates) {
			if (i == stateToCheck)
				count++;
		}

		return count;
	}

}
