package FireCells;

import cells.SquareCell;
import javafx.scene.paint.Color;

public abstract class FireCell extends SquareCell {
	private boolean isEmpty;
	private boolean isBurning;
	
	public FireCell(double x, double y, Color color, double w, double h, double probCatch) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}

	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public boolean isBurning() {
		return isBurning;
	}
	
	public void setBurning(boolean isBurning) {
		this.isBurning = isBurning;
	}
	
	public abstract FireCell update(FireCell[] cells);
}
