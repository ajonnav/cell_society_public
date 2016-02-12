package cells;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class SegregationTypeSquareCell extends SegregationSquareCell {
	protected double tPercentage;
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
	public abstract boolean cellUnsatisfied(SegregationSquareCell[] cells);
	
	protected double getNumOfState(ArrayList<Integer> neighbors, int stateToCheck, SegregationSquareCell[] cells) {
		double count = 0.0;
		for (int i : neighbors) {
			if (cells[i].getState() == stateToCheck)
				count++;
		}

		return count;
	}

}
