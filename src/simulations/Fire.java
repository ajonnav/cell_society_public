package simulations;

import automaton.AutomatonDisplay;

public class Fire extends CA {
	private double probCatch;
	private;
	
	public Fire(int width, int height, int[] states, AutomatonDisplay a) {
		super(width, height, states, a); //super(mapArgs, a);
		// TODO Auto-generated constructor stub
		probCatch = Double.parseDouble(mapArgs.get("probCatch"));
		allCells;
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateCells() {
		// TODO Auto-generated method stub

	}

}
