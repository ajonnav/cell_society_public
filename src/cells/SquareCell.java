package cells;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class SquareCell extends Cell {
	/**
	 * Constructor for SquareCell that will create a square cell of a certain color
	 */
	public SquareCell(double x, double y, Color c, double w, double h){
		super(x, y, c, w, h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(getCellColor());
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
		//gc.strokeRect(getX(), getY(), getWidth(), getHeight());
	}
}
