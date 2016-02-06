package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class TypeOneSegregationSquareCell extends SegregationSquareCell {
	private double tPercentage;

	public TypeOneSegregationSquareCell(double x, double y, double w, double h,
			double t) {
		super(x, y, Color.BLUE, w, h);
		tPercentage = t;
		state = 1;

		// TODO Auto-generated constructor stub
	}

	public TypeOneSegregationSquareCell(SegregationSquareCell v, double t) {
		super(v.getX(), v.getY(), Color.BLUE, v.getWidth(), v.getHeight());
		tPercentage = t;
		state = 1;
	}

	@Override
	public SegregationSquareCell update(SegregationSquareCell[] cells, SegregationSquareCell [] tempChange) {
		if (cellUnsatisfied(cells)) {
			VacantSegregationSquareCell returnCell = new VacantSegregationSquareCell(
					getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(getNeighbor());
			moveRandomly(tempChange);
			return returnCell;
		}
		return this;
	}

	private boolean cellUnsatisfied(SegregationSquareCell[] cells) {
		double numSimilar = getNumOfState(getNeighbor(), state, cells);
		double numNotSimilar = getNumOfState(getNeighbor(), 2, cells);
		return ((numSimilar / (numNotSimilar + numSimilar)) < tPercentage);
	}

	private double getNumOfState(ArrayList<Integer> neighbors,
			int stateToCheck, SegregationSquareCell[] cells) {
		double count = 0.0;
		for (int i : neighbors) {
			if (cells[i].getState() == stateToCheck)
				count++;
		}

		return count;
	}

	private void moveRandomly(SegregationSquareCell[] cells) {
		boolean go = true;
		while (go) {
			int randCell = (int) (Math.random() * (cells.length - 1));
			if (cells[randCell].getState() == 0) {
				SegregationSquareCell temp = cells[randCell];
				cells[randCell] = new TypeOneSegregationSquareCell(temp,
						tPercentage);
				cells[randCell].setNeighbor(temp.getNeighbor());
				go = false;
			}
		}
	}

}
