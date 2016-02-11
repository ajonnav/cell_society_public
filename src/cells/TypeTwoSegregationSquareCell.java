package cells;

import javafx.scene.paint.Color;

public class TypeTwoSegregationSquareCell extends SegregationTypeSquareCell {
	private static final int STATE = 2;
	private static final int OTHER_STATE = 1;
	private static final Color COLOR = Color.RED;

	public TypeTwoSegregationSquareCell(double x, double y, double w, double h,
			double t) {
		super(x, y, COLOR, w, h, t);
		// TODO Auto-generated constructor stub
		state = STATE;
	}
/**
 * Take any type of SegregationSquareCell and create a new version of TypeSegregationCell
 * @param v
 * @param t
 */
	public TypeTwoSegregationSquareCell(SegregationSquareCell v, double t) {
		super(v.getX(), v.getY(), COLOR, v.getWidth(), v.getHeight(), t);
		state = STATE;
	}

}
