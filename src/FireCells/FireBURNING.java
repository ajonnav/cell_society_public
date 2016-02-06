package FireCells;

import javafx.scene.paint.Color;

public class FireBURNING extends FireCell {
	private static final boolean isEmpty = false;
	private static final double ZERO = 0;
	private static final boolean isBurning = true;
	
	public FireBURNING(double x, double y, double w, double h) {
		super(x, y, Color.DARKRED, w, h, ZERO);
		// TODO Auto-generated constructor stub
		setEmpty(isEmpty);
		setBurning(isBurning);
	}

	@Override
	public FireCell update(FireCell[] cells) {
		// TODO Auto-generated method stub
		return new FireEMPTY(getX(), getY(), getWidth(), getHeight());
	}

}
