package ForgingAntCells;

import java.util.Comparator;
import java.util.List;

import cells.Cell;
import ForgingAntCells.ForgingAntCell;
import javafx.scene.paint.Color;
import slot.Slot;

public class GroundCell extends ForgingAntCell {
	private Slot mySlot;
	private double homePheromone;
	private double foodPheromone;
	private double pheromoneLimit;
	private double diffusionRate;
	private double evapRate;
	private boolean home;
	private boolean food;
	
	public GroundCell(double pheromoneLimit, double diffusionRate, double evapRate, boolean home, boolean food, Slot s) {
		super(Color.TRANSPARENT);
		homePheromone = 0;
		foodPheromone = 0;
		this.pheromoneLimit = pheromoneLimit;
		this.diffusionRate = diffusionRate;
		this.evapRate = evapRate;
		this.home = home;
		this.food = food;
		this.mySlot = s;
	}
	
	public void sethomeHormone(double addHormone) {
		if (homePheromone < pheromoneLimit) {
			homePheromone += addHormone;
		}
	}
	
	public void setfoodHormone(double addHormone) {
		if (foodPheromone < pheromoneLimit) {
			foodPheromone += addHormone;
		}
	}

	public double gethomeHormone() {
		return homePheromone;
	}
	
	public double getfoodHormone() {
		return foodPheromone;
	}
	
	public Slot getSlot() {
		return mySlot;
	}
	
	public void update(GroundCell[] cells) {
	}
	
	public void update() {
		foodPheromone -= evapRate;
		homePheromone -= evapRate;
		if (foodPheromone < 0) {
			foodPheromone = 0;
		} else if (homePheromone < 0) {
			homePheromone = 0;
		}
	}
	
	public double[] getDiffusionAmount() {
		List<GroundCell> neighbors = getGroundCellsFromSlot(mySlot.getNeighbors());
		double[] pheromoneIncrease = new double[]{0,0}; //0 is food, 1 is home 
		for (GroundCell g : neighbors) {
			pheromoneIncrease[0] += g.gethomeHormone()*diffusionRate;
			pheromoneIncrease[1] += g.getfoodHormone()*diffusionRate;
		}
		return pheromoneIncrease;
	}
	
	//use comparable or comparator to sort by either food hormones or home hormones?
	static Comparator<GroundCell> foodComparator() {
		return new Comparator<GroundCell>() {
		@Override
			public int compare(GroundCell c1, GroundCell c2) {
				if (c1.getfoodHormone() != c2.getfoodHormone()) {
					return c1.getfoodHormone() > c2.getfoodHormone() ? 1 : -1;
				}
				return 0;
			}
		};

	}
	
	static Comparator<GroundCell> homeComparator() {
		return new Comparator<GroundCell>() {
		@Override
			public int compare(GroundCell c1, GroundCell c2) {
				if (c1.gethomeHormone() != c2.gethomeHormone()) {
					return c1.gethomeHormone() > c2.gethomeHormone() ? 1 : -1;
				}
				return 0;
			}
		};
	}

}
