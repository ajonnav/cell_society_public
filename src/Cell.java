
import javafx.scene.canvas.GraphicsContext;
public abstract class Cell {
	private int state;
	private double xCoord, yCoord;
	public Cell(int currentState, double x, double y) {
		state = currentState;
		xCoord = x;
		yCoord = y;
	}
	
	/**
	 * @return state of cell
	 */
	public int getState(){
		return state;
	}
	/**
	 * Updates cell state
	 * @param newState
	 */
	public void setState(int newState){
		state = newState;
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
	
	public void setX(double newXcoord){
		xCoord = newXcoord;
	}
	
	public void setY(double newYcoord){
		xCoord = newYcoord;
	}
	public abstract void draw(GraphicsContext gc);
}
