package cells;

import javafx.scene.paint.Color;

public class SegregationSlotCell extends SegregationTypeSquareCell {

	public SegregationSlotCell( Color c,
			double t, int s) {
		super(0.0, 0.0, c, 0.0, 0.0, t);
		setState(s);
		// TODO Auto-generated constructor stub
	}

	public SegregationSlotCell(SegregationSquareCell v, double t, int type) {
		super(v, t, type);
		// TODO Auto-generated constructor stub
	}

}
