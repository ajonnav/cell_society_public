package cells;

import javafx.scene.paint.Color;

public class TypeTwoSegregationSquareCell extends SegregationTypeSquareCell {

	private static final Color COLOR = Color.RED;

	public TypeTwoSegregationSquareCell(double x, double y, double w, double h,
			double t) {
		super(x, y, COLOR, w, h, t, 2);
		// TODO Auto-generated constructor stub
	}
/**
 * Take any type of SegregationSquareCell and create a new version of TypeSegregationCell
 * @param v
 * @param t
 */
	public TypeTwoSegregationSquareCell(SegregationSquareCell v, double t) {
		super(v, t, 2);
	}
	

}
