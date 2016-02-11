package cells;

import javafx.scene.paint.Color;

public class VacantSegregationSquareCell extends SegregationSquareCell {
	private static final int STATE = 0;
	public VacantSegregationSquareCell(double x, double y, double w,
			double h) {
		super(x, y, Color.WHITE, w, h);
		// TODO Auto-generated constructor stub
		setState(STATE);
	}
	/**
	 * Always returns false - a vacant cell will never be unsatisfied.
	 */
	public boolean cellUnsatisfied(SegregationSquareCell[] cells){
		return false;
	}

}
