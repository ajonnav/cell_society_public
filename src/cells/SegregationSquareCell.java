package cells;

import javafx.scene.paint.Color;

public abstract class SegregationSquareCell extends SquareCell {
	protected int state;
	public SegregationSquareCell(double x, double y, Color c, double w, double h) {
		super(x, y, c, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int newState){
		state = newState;
	}	
	public abstract boolean cellUnsatisfied(SegregationSquareCell [] cells);
	
	

}
