package FireCells;

import javafx.scene.paint.Color;

public class FireEMPTY extends FireCell {
	private static final boolean isEmpty = true;
	private static final double ZERO = 0;
	
	public FireEMPTY(double x, double y, double w, double h) {
		super(x, y, Color.YELLOW, w, h, ZERO);
		// TODO Auto-generated constructor stub
		setEmpty(isEmpty);
	}

	@Override
	public FireCell update(FireCell[] cells) {
		// TODO Auto-generated method stub
		return this;
	}

}