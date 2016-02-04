package cells;

import javafx.scene.paint.Color;

public class DeadGOLSquareCell extends GOLSquareCell{

	public DeadGOLSquareCell(double x, double y, int w, int h) {
		super(x, y, Color.GRAY, w, h);
	}

}
