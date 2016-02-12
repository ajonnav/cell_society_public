package ForgingAntCells;

import cells.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GroundCell extends Cell {
	private double homeHormone;
	private double foodHormone;
	private double hormoneLimit;
	
	public GroundCell(double x, double y, Color color, double w, double h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
	
	public void sethomeHormone(double addHormone) {
		if (homeHormone < hormoneLimit) {
			homeHormone += addHormone;
		}
	}
	
	public void setfoodHormone(double addHormone) {
		if (foodHormone < hormoneLimit) {
			foodHormone += addHormone;
		}
	}

	public double gethomeHormone() {
		return homeHormone;
	}
	
	public double getfoodHormone() {
		return foodHormone;
	}
	
	public void update(GroundCell[] cells) {
		//evaporation, decrease hormone levels by certain amount
		//check for diffusion? based on neighbors, increase by certain amount?
	}
	
	//use comparable or comparator to sort by either food hormones or home hormones?

}
