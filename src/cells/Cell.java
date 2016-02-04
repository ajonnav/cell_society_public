package cells;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public abstract class Cell {
	private Color cellColor;
	private double xCoord, yCoord;
	public Cell(double x, double y, Color color) {
		setCellColor(color);
		xCoord = x;
		yCoord = y;
	}

	/**
	 * returns x pixel coordinate of cell
	 * @return xCoord
	 */
	public double getX(){
		return xCoord;
	}
	/**
	 * y pixel coordinate of a cell
	 * @return yCoord
	 */
	public double getY(){
		return yCoord;
	}
	/**
	 * Sets X coordinate
	 * @param newXcoord
	 */
	public void setX(double newXcoord){
		xCoord = newXcoord;
	}
	/**
	 * Set Y coordinate
	 * @param newYcoord
	 */
	public void setY(double newYcoord){
		xCoord = newYcoord;
	}
	public Color getCellColor() {
		return cellColor;
	}

	public void setCellColor(Color cellColor) {
		this.cellColor = cellColor;
	}

	/**
	 * This Class will vary from extension to extension of the Cell class.
	 * Every inherited class will require GraphicsContext to draw.
	 * @param gc
	 */
	public abstract void draw(GraphicsContext gc);
}
