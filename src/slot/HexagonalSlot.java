package slot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import cells.Cell;

public class HexagonalSlot implements Slot {
	private int index, side, row, col;
	private double xCoord, yCoord;
	private List<Cell> occupants;
	private List<Slot> neighbors;
	
	public HexagonalSlot(int r, int c, int s, int n) {
		// TODO Auto-generated constructor stub
		row = r;
		col = c;
		side = s;
		index = n;
		occupants = new ArrayList<Cell>();
		neighbors = new ArrayList<Slot>();
		setCoordinates();
	}

	@Override
	public void setNeighbors(Collection<Slot> newNeighbors) {
		neighbors = new ArrayList<Slot>(newNeighbors);
	}
	private void setCoordinates(){
		double h = trig(30.0, 1);
		double r = trig(30.0, 2);
		yCoord = row * (h + side);
		if(row % 2 == 0){
			xCoord = col * r * 2;
		}else{
			xCoord = col * r * 2 + 2;
		}
	}
	private double trig(double angleInDegrees, int trigFunc){
		double angle = radAngle(angleInDegrees);
		if(trigFunc == 1){
			double h = Math.toDegrees(Math.sin(angle));
			h *= side;
			return h;
		}else{
			double r = Math.toDegrees(Math.cos(angle));
			r *= side;
			return r;
		}
	}
	private double radAngle(double angleInDegrees){
		return Math.toRadians(angleInDegrees);
	}
	@Override
	public void addNeighbor(Slot newSlot) {
		neighbors.add(newSlot);
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public Collection<Slot> getNeighbors() {
		return neighbors;
	}

	@Override
	public void draw(GraphicsContext gc, Color c) {
		gc.setFill(c);
		//corners will be ordered in a clockwise fashion, starting at reference point xCoord, yCoord
		double [] xPoints = getXPoints();
		double [] yPoints = getYPoints();
		gc.fillPolygon(xPoints, yPoints, 6);

	}
	
	double [] getXPoints(){
		double [] xPoints = new double[6];
		xPoints[0] = xCoord;
		xPoints[1] = xCoord + trig(30, 2);
		xPoints[2] = xCoord + 2*trig(30,2);
		xPoints[3] = xCoord + 2*trig(30,2);
		xPoints[4] = xCoord + trig(30, 2);
		xPoints[5] = xCoord;
		return xPoints;
	}
	
	double [] getYPoints(){
		double [] yPoints = new double[6];
		yPoints[0] = yCoord;
		yPoints[1] = yCoord - trig(30, 1);
		yPoints[2] = yCoord;
		yPoints[3] = yCoord + side;
		yPoints[4] = yCoord + side + trig(30, 1);
		yPoints[5] = yCoord + side;
		return yPoints;
	}

	@Override
	public Collection<Cell> getOccupants() {
		return occupants;
	}

	@Override
	public void setOccupants(Collection<Cell> cells) {
		occupants = new ArrayList<Cell>(cells);

	}

	@Override
	public int[] getRowCol() {
		int[] ret = {row, col};
		return ret;
	}

}
