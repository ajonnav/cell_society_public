package cells;

import javafx.scene.paint.Color;

public class TypeOneSegregationSquareCell extends SegregationTypeSquareCell {
	private double tPercentage;
	private static final int STATE = 1;
	private static final int OTHER_STATE = 2;
	private static final Color COLOR = Color.BLUE;
	public TypeOneSegregationSquareCell(double x, double y, double w, double h,
			double t) {
		super(x, y, COLOR, w, h, t);
		setState(STATE);

	}

	public TypeOneSegregationSquareCell(SegregationSquareCell v, double t) {
		super(v.getX(), v.getY(), COLOR, v.getWidth(), v.getHeight(), t);
		setState(STATE);
	}



}
