/**
 * 
 * @author omer
 * 
 *         This class represents each individual moves the user may take. This
 *         will be implemented into a stack structure to determine if the motion
 *         is legal
 */

public class Motion {
	Direction d, undo;
	Tile t;
	Motion left, right, up, down, parent;

	/**
	 * 
	 * @param t
	 *            is the current tile being looked at
	 * @param d
	 *            is the direction the tile will be moved
	 */
	public Motion(Tile t, Direction d) {
		this.d = d;
		this.t = t;
		if (d == Direction.DOWN) {
			undo = Direction.UP;
		} else if (d == Direction.UP) {
			undo = Direction.DOWN;
		} else if (d == Direction.LEFT) {
			undo = Direction.RIGHT;
		} else if (d == Direction.RIGHT) {
			undo = Direction.LEFT;
		}
	}

	public void figureOutNextMoves() {
		// gotta figure out what the next move is gonna be;
	}

	public boolean isValidMove(Direction d) {
		if (d == Direction.DOWN) {// check if tile is above the zero tile
			if (t.getDown().getNumber().equals("0")) {
				return true;
			} else {
				return false;
			}

		} else if (d == Direction.UP) {// check if tile is below the zero tile
			if (t.getUp().getNumber().equals("0")) {
				return true;
			} else {
				return false;
			}
		} else if (d == Direction.LEFT) {// check if tile is to the right of the
											// zero tile

			if (t.getLeft().getNumber().equals("0")) {
				return true;
			} else {
				return false;
			}

		} else if (d == Direction.RIGHT) {// check if the tile is to the left of
											// the zero tile
			if (t.getRight().getNumber().equals("0")) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

}
