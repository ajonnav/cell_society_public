package cells;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public abstract class Cell {
	private Color cellColor;
	private double xCoord, yCoord;
	private ArrayList<Integer> neighbor;
	private double width, height;
	public Cell(double x, double y, Color color, double w, double h) {
		setCellColor(color);
		xCoord = x;
		yCoord = y;
		neighbor = new ArrayList<Integer>();
		setWidth(w);
		setHeight(h);
	}

	/**
	 * This Class will vary from extension to extension of the Cell class.
	 * Every inherited class will require GraphicsContext to draw.
	 * @param gc
	 */
	public abstract void draw(GraphicsContext gc);
	
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
		yCoord = newYcoord;
	}
	
	public double getWidth() {
		return width;
	}
	
	protected void setWidth(double w) {
		this.width = w;
	}
	
	public double getHeight() {
		return height;
	}
	
	protected void setHeight(double h) {
		this.height = h;
	}
	
	public Color getCellColor() {
		return cellColor;
	}

	public void setCellColor(Color cellColor) {
		this.cellColor = cellColor;
	}

	public ArrayList<Integer> getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(ArrayList<Integer> list) {
		this.neighbor = list;
	}
}
