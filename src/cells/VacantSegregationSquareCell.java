package cells;

import javafx.scene.paint.Color;

public class VacantSegregationSquareCell extends SegregationSquareCell {

	public VacantSegregationSquareCell(double x, double y, double w,
			double h) {
		super(x, y, Color.WHITE, w, h);
		// TODO Auto-generated constructor stub
		state = 0;
	}

	@Override
	public SegregationSquareCell update(SegregationSquareCell[] cells, SegregationSquareCell [] tempChange, SegregationSquareCell [] noGo) {
		// TODO Auto-generated method stub
		return this;
	}

}
