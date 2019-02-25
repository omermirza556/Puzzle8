
import java.util.Random;
import java.util.Stack;

/**
 * 
 * @author omer
 * 
 *         This is the model class that contains a 2D array of tiles which will
 *         be used as a gameboard
 */
public class Model {

	int xLength = 3, yLength = 3, side, shuffleMoves = 10;
	boolean isValidMove = false;
	private Tile[][] gameBoard = new Tile[xLength][yLength];
	Stack<Motion> moves = new Stack<Motion>();
	static final String GOAL_STATE = "012345678";

	public Model() {

		// creates the base board
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {

				// System.out.println("i: " + i + " k:" + k + " " + (i + (3 *
				// k)));
				gameBoard[i][k] = new Tile(i, k, Integer.toString(i + (3 * k)));

			}
		}

		this.side = gameBoard[0][0].getSize();

		connect();
		shuffle();

	}

	/**
	 * According to reid and hunter I have to use a bfs search algorithm, reid's
	 * advice was to make sure it doesn't backtrack
	 */
	public void AI() {
		// clone = this.gameBoard;
		// System.out.println("Got it");
		Graph g = new Graph(gameBoard);
		g.doEverything();
		// g.makeStack();
		// g.solve();
		// Graph g = new Graph(clone);
		// g.solve();

	}

	/**
	 * the shuffle method shuffles the board
	 * 
	 * lmao this is so inefficient
	 */
	public void shuffle() {
		/*
		 * Undos the moves to start with a clear board
		 */
		while (!moves.isEmpty()) {
			undo();
		}

		while (moves.size() < shuffleMoves) {
			// need to make sure it doesn't repeat a move
			Random rand = new Random();
			int x = rand.nextInt(3);
			int y = rand.nextInt(3);

			if (!moves.isEmpty()) {
				if (Math.floor(moves.peek().t.getX() / side) == x && Math.floor(moves.peek().t.getY() / side) == y) {
					x = rand.nextInt(3);
					y = rand.nextInt(3);
				}

			}
			click(x * side, y * side);
		}
		/*
		 * Below is a check to make sure the board doesn't shuffle into the goal
		 * state
		 */
		if (Model.GOAL_STATE.equals(this.currentState())) {
			shuffle();
		}
	}

	/**
	 * This method pops off the last move in the stack and does the opposite
	 * motion to put the square back in its original space
	 */
	public void undo() {

		if (!moves.isEmpty()) {
			Motion theMove = moves.pop();
			if (theMove.undo == Direction.LEFT) {
				undoLeft(theMove.t);
				connect();
			}
			if (theMove.undo == Direction.RIGHT) {
				undoRight(theMove.t);
				connect();
			}
			if (theMove.undo == Direction.UP) {
				undoUp(theMove.t);
				connect();
			}
			if (theMove.undo == Direction.DOWN) {
				undoDown(theMove.t);
				connect();
			}
		} else {
			System.out.println("IT'S EMPTY");
		}

	}

	/**
	 * the user clicks on the selected square and it switches places with the
	 * zero square provided it is next to it
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public boolean click(int x, int y) {

		int xVal = (int) Math.floor(x / side);
		int yVal = (int) Math.floor(y / side);

		if (gameBoard[xVal][yVal].getNumber().equals("0") || !gameBoard[xVal][yVal].isNextToZero()) {

			// System.out.println("Invalid move");
			// isValidMove = false;
			return false;

		}
		if (gameBoard[xVal][yVal].isNextToZero()) {

			if (gameBoard[xVal][yVal].whereIsZero() == Direction.LEFT) {
				left(xVal, yVal);
				moves.push(new Motion(gameBoard[xVal - 1][yVal], Direction.LEFT));
				// System.out.println("Adding to the stack");

			}

			if (gameBoard[xVal][yVal].whereIsZero() == Direction.RIGHT) {

				right(xVal, yVal);
				moves.push(new Motion(gameBoard[xVal + 1][yVal], Direction.RIGHT));
				// System.out.println("Adding to the stack");
			}

			if (gameBoard[xVal][yVal].whereIsZero() == Direction.UP) {

				up(xVal, yVal);
				moves.push(new Motion(gameBoard[xVal][yVal - 1], Direction.UP));
				// System.out.println("Adding to the stack");
			}

			if (gameBoard[xVal][yVal].whereIsZero() == Direction.DOWN) {

				down(xVal, yVal);
				moves.push(new Motion(gameBoard[xVal][yVal + 1], Direction.DOWN));
				// System.out.println("Adding to the stack");
			}

			connect();
			return true;
		}
		return false;
	}

	/**
	 * prints the game board
	 */
	public void printGameBoard() {
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {

				System.out.println("i: " + i + " k: " + k + "," + gameBoard[i][k].getNumber());

			}
		}
	}

	/**
	 * Moves the square to the left
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void left(int xVal, int yVal) {
		// System.out.println(Direction.LEFT);
		Tile temp = gameBoard[xVal][yVal];
		Tile zero = gameBoard[xVal - 1][yVal];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);
		gameBoard[xVal][yVal] = zero;
		gameBoard[xVal - 1][yVal] = temp;

	}

	/**
	 * Moves the square to the right
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void right(int xVal, int yVal) {
		// System.out.println(Direction.RIGHT);
		Tile temp = gameBoard[xVal][yVal];
		Tile zero = gameBoard[xVal + 1][yVal];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);
		gameBoard[xVal + 1][yVal] = temp;
		gameBoard[xVal][yVal] = zero;

	}

	/**
	 * Moves the square to the up
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void up(int xVal, int yVal) {
		// System.out.println(Direction.UP);
		Tile temp = gameBoard[xVal][yVal];
		Tile zero = gameBoard[xVal][yVal - 1];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);
		gameBoard[xVal][yVal - 1] = temp;
		gameBoard[xVal][yVal] = zero;

	}

	/**
	 * Moves the square to the down
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void down(int xVal, int yVal) {
		// System.out.println(Direction.DOWN);
		Tile temp = gameBoard[xVal][yVal];
		Tile zero = gameBoard[xVal][yVal + 1];
		int zeroX = zero.getX();
		int zeroY = zero.getY();
		zero.changeCoor(temp.getX(), temp.getY());
		temp.changeCoor(zeroX, zeroY);
		gameBoard[xVal][yVal] = zero;
		gameBoard[xVal][yVal + 1] = temp;

	}

	/**
	 * 
	 * Undo to the down position
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void undoDown(Tile t) {
		int xVal = (int) Math.floor(t.getX() / side);
		int yVal = (int) Math.floor(t.getY() / side);
		down(xVal, yVal);
	}

	/**
	 * 
	 * Undo to the up position
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void undoUp(Tile t) {
		int xVal = (int) Math.floor(t.getX() / side);
		int yVal = (int) Math.floor(t.getY() / side);

		up(xVal, yVal);
	}

	/**
	 * 
	 * Undo to the right position
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void undoRight(Tile t) {
		int xVal = (int) Math.floor(t.getX() / side);
		int yVal = (int) Math.floor(t.getY() / side);
		right(xVal, yVal);
	}

	/**
	 * 
	 * Undo to the left position
	 * 
	 * @param xVal
	 *            the x value in the 2D array
	 * @param yVal
	 *            the Y value in the 2D array
	 * 
	 */
	public void undoLeft(Tile t) {
		int xVal = (int) Math.floor(t.getX() / side);
		int yVal = (int) Math.floor(t.getY() / side);
		left(xVal, yVal);
	}

	public void doEverything() {
		connect();
		whereAreMyNeighbors();
	}

	/**
	 * Re-establishes the proximity of the square to each other
	 */
	public void connect() {
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {

				if (i != 0) {
					gameBoard[i][k].setLeft(gameBoard[i - 1][k]);

				} else {
					gameBoard[i][k].setLeft(null);
				}

				if (i != 2) {
					gameBoard[i][k].setRight(gameBoard[i + 1][k]);

				} else {
					gameBoard[i][k].setRight(null);
				}

				if (k != 0) {
					gameBoard[i][k].setUp(gameBoard[i][k - 1]);

				} else {
					gameBoard[i][k].setUp(null);
				}

				if (k != 2) {
					gameBoard[i][k].setDown(gameBoard[i][k + 1]);

				} else {
					gameBoard[i][k].setDown(null);
				}

			}
		}
	}

	/**
	 * Prints each squares neighbors
	 */
	public void whereAreMyNeighbors() {
		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				System.out.println("This " + gameBoard[i][k].getNumber());
				if (gameBoard[i][k].getLeft() != null) {
					System.out.println("Left : " + gameBoard[i][k].getLeft().getNumber());
				}
				if (gameBoard[i][k].getRight() != null) {
					System.out.println("Right : " + gameBoard[i][k].getRight().getNumber());
				}
				if (gameBoard[i][k].getUp() != null) {
					System.out.println("Up : " + gameBoard[i][k].getUp().getNumber());
				}

				if (gameBoard[i][k].getDown() != null) {
					System.out.println("Down : " + gameBoard[i][k].getDown().getNumber());
				}
				System.out.println("*****");
			}

		}
	}

	public Tile whereIsZero(Tile[][] gameBoard) {

		for (int i = 0; i < xLength; i++) {
			for (int k = 0; k < yLength; k++) {
				if (gameBoard[i][k].getNumber().equals("0")) {
					// System.out.println("The zero is at Tile " +
					// gameBoard[i][k]);
					return gameBoard[i][k];
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * @return the current state of the board
	 */
	public String currentState() {
		String state;
		state = gameBoard[0][0].getNumber() + gameBoard[1][0].getNumber() + gameBoard[2][0].getNumber()
				+ gameBoard[0][1].getNumber() + gameBoard[1][1].getNumber() + gameBoard[2][1].getNumber()
				+ gameBoard[0][2].getNumber() + gameBoard[1][2].getNumber() + gameBoard[2][2].getNumber();
		// gameBoard[0][0];
		return state;
	}

	/*
	 * 
	 * Getters and setters
	 */
	public int getxLength() {
		return xLength;
	}

	public void setxLength(int xLength) {
		this.xLength = xLength;
	}

	public int getyLength() {
		return yLength;
	}

	public void setyLength(int yLength) {
		this.yLength = yLength;
	}

	public Tile[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Tile[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Stack<Motion> getMoves() {
		return moves;
	}

	public void setMoves(Stack<Motion> moves) {
		this.moves = moves;
	}

}
