package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class TypeTwoSegregationSquareCell extends SegregationSquareCell {
	private double tPercentage;
	public TypeTwoSegregationSquareCell(double x, double y, double w,
			double h, double t) {
		super(x, y, Color.RED, w, h);
		// TODO Auto-generated constructor stub
		state = 2;
		tPercentage = t;
	}
	public TypeTwoSegregationSquareCell(SegregationSquareCell v, double t) {
		super(v.getX(), v.getY(), Color.BLUE, v.getWidth(), v.getHeight());
		tPercentage = t;
		state = 2;
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
		double numNotSimilar = getNumOfState(getNeighbor(), 1, cells);
		return ((numSimilar / ( numNotSimilar + numSimilar)) < tPercentage);
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
				cells[randCell] = new TypeTwoSegregationSquareCell(temp,
						tPercentage);
				cells[randCell].setNeighbor(temp.getNeighbor());
				go = false;
			}
		}
	}
}
