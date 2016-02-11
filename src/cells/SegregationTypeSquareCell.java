package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class SegregationTypeSquareCell extends SegregationSquareCell {
	protected double tPercentage;
	private int OTHER_STATE;
	public SegregationTypeSquareCell(double x, double y, Color c, double w,
			double h, double t) {
		super(x, y, c, w, h);
		tPercentage = t;
	}
	
	public SegregationTypeSquareCell(SegregationSquareCell v, double t){
		super(v.getX(), v.getY(), v.getCellColor(), v.getWidth(), v.getHeight());
		tPercentage = t;
	}

	@Override
	public boolean cellUnsatisfied(SegregationSquareCell[] cells) {
		double numSimilar = getNumOfState(getNeighbor(), state, cells);
		double numNotSimilar = getNumOfState(getNeighbor(), OTHER_STATE, cells);
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
