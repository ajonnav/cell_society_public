package simulations;
import automaton.*;
import factory.GridFactory;
import grid.*;

public class AniCA {
	
	AniAnyGrid grid;
	private String edgeType;
	
	public AniCA (XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		grid = (AniAnyGrid) GridFactory.create(edgeType, xmlArgs, autoDisp);
		grid.setUpSlots();
		grid.calculateNeighbors();
	}

}
