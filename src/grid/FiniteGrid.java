package grid;

import automaton.AutomatonDisplay;
import automaton.XMLArgs;
import slot.*;

public class FiniteGrid extends Grid implements AnyGrid {

	public FiniteGrid(int r, int c, int w, int h, String s,
			Direction[] neighborsToCheck) {
		super(r, c, w, h, s, neighborsToCheck);
	}

	public FiniteGrid(XMLArgs xmlArgs, AutomatonDisplay autoDisp,
			Direction[] neighborsToCheck) {
		super(xmlArgs.getAsInt("numRow"), xmlArgs.getAsInt("numCol"), xmlArgs
				.getAsInt("simWidth") / xmlArgs.getAsInt("numCol"), xmlArgs
				.getAsInt("simHeight") / xmlArgs.getAsInt("numRow"), xmlArgs
				.getAsString("edgeType"), neighborsToCheck);
	}

	/**
	 * goes through the rows and cols and builds a slot for each spot. how the
	 * slot coordinates are made depend on the slot type. assigns an index in a
	 * linear fashion.
	 */

	public void setNeighbors() {
		for (Slot s : slotList) {
			for (Direction d : getDirections()) {
				addIfInBounds(s, d);
			}
		}
	}

	protected void addIfInBounds(Slot s, Direction d) {
		int[] rcDelta = new int[2];
		int row = s.index() / getNumCol();
		int col = s.index() % getNumCol();
		rcDelta[0] = row + d.getVec()[0];
		rcDelta[1] = col + d.getVec()[1];
		if (inBounds(rcDelta)) {
			s.addNeighbor(slotList.get(rcDelta[0] * getNumCol() + rcDelta[1]));
		}
	}

	protected boolean inBounds(int[] rcDelta) {
		return (0 <= rcDelta[0] && rcDelta[0] < getNumRow() && 0 <= rcDelta[1]
				&& rcDelta[1] < getNumCol() && rcDelta[0] != rcDelta[1]);
	}
}
