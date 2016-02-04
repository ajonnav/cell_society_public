package cells;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class SquareCell extends Cell {
	private int width, height;
	/**
	 * Constructor for SquareCell that will create a square cell of a certain color
	 */
	public SquareCell(double x, double y, Color c, int w, int h){
		super(x, y, c);
		setWidth(w);
		setHeight(h);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(getCellColor());
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	private int getWidth() {
		return width;
	}
	private void setWidth(int width) {
		this.width = width;
	}
	private int getHeight() {
		return height;
	}
	private void setHeight(int height) {
		this.height = height;
	}

}
