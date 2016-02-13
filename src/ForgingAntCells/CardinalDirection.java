package ForgingAntCells;

public enum CardinalDirection {
	N, NE, E, SE, S, SW, W, NW;

	public CardinalDirection valueOf(int dx, int dy) {
		if (dx == 0) {
			if (dy == -1) {
				return W;
			} else if (dy == 1) {
				return E;
			}	
		} else if (dx == -1) {
			if (dy == -1) {
				return NW;
			} else if (dy == 0) {
				return N;
			} else if (dy == 1) {
				return NE;
			}
		} else if (dx == 1) {
			if (dy == -1) {
				return SW;
			} else if (dy == 0) {
				return S;
			} else if (dy == 1) {
				return SE;
			}
		}
		return null;
	}
}
