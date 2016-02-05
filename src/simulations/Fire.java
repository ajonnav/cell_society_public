package simulations;

import java.util.Map;

import automaton.AutomatonDisplay;

public class Fire extends CA {
	private double probCatch;
	private double allCells;
	
	public Fire (Map<String, String> mapArgs, AutomatonDisplay a) {
		super(mapArgs, a);
		// TODO Auto-generated constructor stub
		probCatch = Double.parseDouble(mapArgs.get("probCatch"));
//		allCells;
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCells() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCells() {
		// TODO Auto-generated method stub
		
	}

}
