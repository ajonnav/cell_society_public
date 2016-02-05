package cells;

import javafx.scene.paint.Color;

public abstract class GOLSquareCell extends SquareCell{

	private boolean isAlive;
	
	public GOLSquareCell(double x, double y, Color c, double w, double h) {
		super(x, y, c, w, h);
		// TODO Auto-generated constructor stub
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public abstract GOLSquareCell update(GOLSquareCell[] cells);
}
