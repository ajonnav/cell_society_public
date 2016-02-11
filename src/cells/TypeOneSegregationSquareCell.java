package cells;

import javafx.scene.paint.Color;

public class TypeOneSegregationSquareCell extends SegregationTypeSquareCell {
	private double tPercentage;
	private static final Color COLOR = Color.BLUE;
	public TypeOneSegregationSquareCell(double x, double y, double w, double h,
			double t) {
		super(x, y, COLOR, w, h, t, 1);
		

	}

	public TypeOneSegregationSquareCell(SegregationSquareCell v, double t) {
		super(v, t, 1);
	}
	/**
	 * Returns true if the number of similar/total neighbors ratio is less than the tPercentage
	 */
	public boolean cellUnsatisfied(SegregationSquareCell[] cells) {
		double numSimilar = getNumOfState(getNeighbor(), STATE, cells);
		double numNotSimilar = getNumOfState(getNeighbor(), OTHER_STATE, cells);
		return ((numSimilar / (numNotSimilar + numSimilar)) < tPercentage);
	}


}
