package cells;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean isUnsatisfied(List<Integer> neighborStates){
		if(getState() == 0) {return false;}else{
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

}
