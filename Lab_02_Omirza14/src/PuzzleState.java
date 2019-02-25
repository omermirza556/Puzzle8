/**
 * This class keeps track of a puzzle state, essentially an instance of a 2D
 * array.
 * 
 * @author omer
 * 
 *         NOTE To future self:
 * 
 *         I know how hard you've been working on this class and while I am not
 *         done, you are (hopefully). I may have janked this code together but
 *         it still works. You, future me, are probably judging me for my
 *         elementary coding style, HOWEVER, you probably forgot how to do any
 *         of this shit. lmao
 * 
 * 
 *         This class holds the current state of the board, note I wrote this
 *         class way before I did the grpah class so it is over kill
 *
 */
public class PuzzleState {
	Tile[][] startState;
	int xLength = 3, yLength = 3;
	int xOfZero = -1, yOfZero = -1;
	Direction toGetHere;
	// Tile topLeft, top, topRight, midLeft, mid, midRight, botLeft, bot,
	// botRight;

	public PuzzleState(Direction toGetHere, Tile[][] startState) {
		this.startState = startState;
		this.toGetHere = toGetHere;
		connect(this);
	}

	public PuzzleState(Tile[][] startState) {
		this.startState = startState;
		this.toGetHere = null;
	}

	/**
	 * This method makes the left child of this puzzle state
	 * 
	 * @return a new puzzlestate
	 */
	public PuzzleState makeLeftChild() {
		if (getTopLeft().getNumber().equals("0") || getMidLeft().getNumber().equals("0")
				|| getBotLeft().getNumber().equals("0")) {
			return null;

		}

		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				if (startState[i][k].getNumber().equals("0")) {
					xOfZero = startState[i][k].getX() / Tile.side;
					yOfZero = startState[i][k].getY() / Tile.side;
				}
			}
		}
		System.out.println("Left: " + xOfZero + "," + yOfZero);
		// return null;
		return new PuzzleState(Direction.LEFT, left(xOfZero, yOfZero));

	}

	/**
	 * makes a top child
	 * 
	 * @return top child puzzle state
	 */
	public PuzzleState makeTopChild() {
		if (getTopLeft().getNumber().equals("0") || getTop().getNumber().equals("0")
				|| getTopRight().getNumber().equals("0")) {
			return null;

		}

		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				if (startState[i][k].getNumber().equals("0")) {
					xOfZero = startState[i][k].getX() / Tile.side;
					yOfZero = startState[i][k].getY() / Tile.side;
				}
			}
		}
		System.out.println("Up " + xOfZero + "," + yOfZero);
		// return null;
		return new PuzzleState(Direction.UP, up(xOfZero, yOfZero));

	}

	/**
	 * makes a right child
	 * 
	 * @return a right child puzzle state
	 */
	public PuzzleState makeRightChild() {
		if (getTopRight().getNumber().equals("0") || getMidRight().getNumber().equals("0")
				|| getBotRight().getNumber().equals("0")) {
			return null;

		}
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				if (startState[i][k].getNumber().equals("0")) {
					xOfZero = startState[i][k].getX() / Tile.side;
					yOfZero = startState[i][k].getY() / Tile.side;
				}
			}
		}
		System.out.println("Right " + xOfZero + "," + yOfZero);
		// return null;
		return new PuzzleState(Direction.RIGHT, right(xOfZero, yOfZero));
	}

	/**
	 * makes a bot child puzzle state
	 * 
	 * @return the puzzle state of the bot child
	 */
	public PuzzleState makeBotChild() {
		if (getBotLeft().getNumber().equals("0") || getBot().getNumber().equals("0")
				|| getBotRight().getNumber().equals("0")) {
			return null;

		}
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				if (startState[i][k].getNumber().equals("0")) {
					xOfZero = startState[i][k].getX() / Tile.side;
					yOfZero = startState[i][k].getY() / Tile.side;
				}
			}
		}
		System.out.println("down " + xOfZero + "," + yOfZero);
		// return null;
		return new PuzzleState(Direction.DOWN, down(xOfZero, yOfZero));
	}

	/**
	 * Moves the square to the left.
	 * 
	 * NOTE: This method works slightly differently than in the model class. In
	 * this instance a new 2D array of squares is created and from there the
	 * tiles are them switched around, but the x and y value coorespond to the
	 * zero rather than the square next to it
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public Tile[][] right(int xVal, int yVal) {
		// System.out.println(Direction.LEFT);
		Tile[][] gameBoard = new Tile[xLength][yLength];
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				gameBoard[i][k] = new Tile(this.startState[i][k].getX() / Tile.side,
						this.startState[i][k].getY() / Tile.side, this.startState[i][k].getNumber());
			}
		}

		Tile temp = gameBoard[xVal + 1][yVal];
		Tile zero = gameBoard[xVal][yVal];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);

		gameBoard[xVal + 1][yVal] = zero;
		gameBoard[xVal][yVal] = temp;
		return gameBoard;

	}

	/**
	 * Moves the square to the right.
	 * 
	 * 
	 * NOTE: This method works slightly differently than in the model class. In
	 * this instance a new 2D array of squares is created and from there the
	 * tiles are them switched around, but the x and y value coorespond to the
	 * zero rather than the square next to it
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public Tile[][] left(int xVal, int yVal) {
		Tile[][] gameBoard = new Tile[xLength][yLength];
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				gameBoard[i][k] = new Tile(this.startState[i][k].getX() / Tile.side,
						this.startState[i][k].getY() / Tile.side, this.startState[i][k].getNumber());
			}
		}

		Tile temp = gameBoard[xVal - 1][yVal];
		Tile zero = gameBoard[xVal][yVal];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);

		gameBoard[xVal][yVal] = temp;
		gameBoard[xVal - 1][yVal] = zero;
		return gameBoard;

	}

	/**
	 * Moves the square to the up.
	 * 
	 * 
	 * NOTE: This method works slightly differently than in the model class. In
	 * this instance a new 2D array of squares is created and from there the
	 * tiles are them switched around, but the x and y value coorespond to the
	 * zero rather than the square next to it
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public Tile[][] down(int xVal, int yVal) {
		Tile[][] gameBoard = new Tile[xLength][yLength];
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				gameBoard[i][k] = new Tile(this.startState[i][k].getX() / Tile.side,
						this.startState[i][k].getY() / Tile.side, this.startState[i][k].getNumber());
			}
		}

		Tile temp = gameBoard[xVal][yVal + 1];
		Tile zero = gameBoard[xVal][yVal];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);
		gameBoard[xVal][yVal] = temp;
		gameBoard[xVal][yVal + 1] = zero;
		return gameBoard;

	}

	/**
	 * Moves the square to the down
	 * 
	 * 
	 * 
	 * NOTE: This method works slightly differently than in the model class. In
	 * this instance a new 2D array of squares is created and from there the
	 * tiles are them switched around, but the x and y value coorespond to the
	 * zero rather than the square next to it
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public Tile[][] up(int xVal, int yVal) {
		Tile[][] gameBoard = new Tile[xLength][yLength];
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				gameBoard[i][k] = new Tile(this.startState[i][k].getX() / Tile.side,
						this.startState[i][k].getY() / Tile.side, this.startState[i][k].getNumber());
			}
		}
		Tile temp = gameBoard[xVal][yVal - 1];
		Tile zero = gameBoard[xVal][yVal];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);
		gameBoard[xVal][yVal - 1] = zero;
		gameBoard[xVal][yVal] = temp;
		return gameBoard;

	}

	/**
	 * this connects the pieces
	 * 
	 * @param s
	 *            any sort of state
	 */
	public void connect(PuzzleState s) {
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {

				if (i != 0) {
					s.startState[i][k].setLeft(s.startState[i - 1][k]);

				} else {
					s.startState[i][k].setLeft(null);
				}

				if (i != 2) {
					s.startState[i][k].setRight(s.startState[i + 1][k]);

				} else {
					s.startState[i][k].setRight(null);
				}

				if (k != 0) {
					s.startState[i][k].setUp(s.startState[i][k - 1]);

				} else {
					s.startState[i][k].setUp(null);
				}

				if (k != 2) {
					s.startState[i][k].setDown(s.startState[i][k + 1]);

				} else {
					s.startState[i][k].setDown(null);
				}

			}
		}
	}

	/*
	 * Getters and setters
	 */
	public Tile[][] getStartState() {
		return startState;
	}

	public void setStartState(Tile[][] startState) {
		this.startState = startState;
	}

	public Tile getTopLeft() {
		return startState[0][0];
	}

	public Tile getTop() {
		return startState[1][0];
	}

	public Tile getTopRight() {
		return startState[2][0];
	}

	public Tile getMidLeft() {
		return startState[0][1];
	}

	public Tile getMid() {
		return startState[1][1];
	}

	public Tile getMidRight() {
		return startState[2][1];
	}

	public Tile getBotLeft() {
		return startState[0][2];
	}

	public Tile getBot() {
		return startState[1][2];
	}

	public Tile getBotRight() {
		return startState[2][2];
	}

	public String returnState() {
		String state;
		state = startState[0][0].getNumber() + startState[1][0].getNumber() + startState[2][0].getNumber()
				+ startState[0][1].getNumber() + startState[1][1].getNumber() + startState[2][1].getNumber()
				+ startState[0][2].getNumber() + startState[1][2].getNumber() + startState[2][2].getNumber();
		// gameBoard[0][0];
		return state;
	}

	public Boolean isItGoalState() {
		if (returnState().equals(Model.GOAL_STATE)) {
			return true;
		} else {
			return false;
		}
	}

}
