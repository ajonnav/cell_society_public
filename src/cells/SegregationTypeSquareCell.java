package cells;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class SegregationTypeSquareCell extends SegregationSquareCell {
	private double tPercentage;
	protected final int STATE;
	final int OTHER_STATE;
	public SegregationTypeSquareCell(double x, double y, Color c, double w,
			double h, double t, int type) {
		super(x, y, c, w, h);
		tPercentage = t;
		if(type == 1){
			STATE = 1;
			OTHER_STATE = 2;
		}else{
			STATE = 2;
			OTHER_STATE = 1;
		}
	}
	
	public SegregationTypeSquareCell(SegregationSquareCell v, double t, int type){
		super(v.getX(), v.getY(), v.getCellColor(), v.getWidth(), v.getHeight());
		if(type == 1){
			STATE = 1;
			OTHER_STATE = 2;
		}else{
			STATE = 2;
			OTHER_STATE = 1;
		}
		tPercentage = t;
	}

	@Override
	public boolean cellUnsatisfied(SegregationSquareCell[] cells) {
		double numSimilar = getNumOfState(new ArrayList<Integer>(getNeighbor()), STATE, cells);
		double numNotSimilar = getNumOfState(new ArrayList<Integer>(getNeighbor()), OTHER_STATE, cells);
		return ((numSimilar / (numNotSimilar + numSimilar)) < tPercentage);
	}
	
	protected double getNumOfState(ArrayList<Integer> neighbors, int stateToCheck, SegregationSquareCell[] cells) {
		double count = 0.0;
		for (int i : neighbors) {
			if (cells[i].getState() == stateToCheck)
				count++;
		}

		return count;
	}
	

}
