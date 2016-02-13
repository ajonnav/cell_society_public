package slot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import cells.Cell;

public class HexagonalSlot implements Slot {
	private int index;
	private double x, y, width, height, side;
	private List<Cell> occupants;
	private List<Slot> neighbors;
	
	public HexagonalSlot(double x, double y, double w, int h, int i) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		side = height/2;
		index = i;
		occupants = new ArrayList<Cell>();
		neighbors = new ArrayList<Slot>();
	}

	@Override
	public void setNeighbors(Collection<Slot> newNeighbors) {
		neighbors = new ArrayList<Slot>(newNeighbors);
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
		xPoints[0] = x;
		xPoints[1] = x + trig(30, 2);
		xPoints[2] = x + 2*trig(30,2);
		xPoints[3] = x + 2*trig(30,2);
		xPoints[4] = x + trig(30, 2);
		xPoints[5] = x;
		return xPoints;
	}
	
	double [] getYPoints(){
		double [] yPoints = new double[6];
		yPoints[0] = y;
		yPoints[1] = y - trig(30, 1);
		yPoints[2] = y;
		yPoints[3] = y + side;
		yPoints[4] = y + side + trig(30, 1);
		yPoints[5] = y + side;
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
	public void addOccupant(Cell cell){
		occupants.add(cell);
	}
}
