
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * This class will traverse the possible combinations of the board state and
 * then give out step by step directions to the goal state
 * 
 * @author omer
 *
 */
public class Graph {
	PuzzleState origin;
	int counter = 0;
	/*
	 * added to flip the instructions to the correct order
	 */
	Stack<String> pathInverse = new Stack<String>();
	/*
	 * Decending order in the graph
	 */
	Queue<String> path = new LinkedList<String>();
	/*
	 * at what level the solution will be found at
	 */
	Map<String, Integer> depth = new HashMap<String, Integer>();
	/*
	 * Keeps track of already existing
	 */
	Map<String, String> history = new HashMap<String, String>();

	public Graph(Tile[][] board) {
		PuzzleState s = new PuzzleState(board);
		origin = s;

	}

	public Graph(PuzzleState s) {
		origin = s;

	}

	public void doEverything() {
		String str = origin.returnState();
		add(str, null);

		while (!path.isEmpty()) {
			String currentState = path.remove();
			up(currentState);
			down(currentState);
			right(currentState);
			left(currentState);
		}

	}

	/**
	 * This method adds a new state to the hashmaps + Queue
	 * 
	 * @param newState
	 * @param oldState
	 */
	public void add(String newState, String oldState) {

		if (!depth.containsKey(newState)) {
			int val = oldState == null ? 0 : depth.get(oldState) + 1;
			depth.put(newState, val);
			path.add(newState);
			history.put(newState, oldState);
		}

	}

	/**
	 * checks to see if the state is part of the solution then it displays the
	 * path needed to get to the solution
	 * 
	 * @param oldState
	 *            previous state
	 * @param newState
	 *            new state
	 */
	public void check(String oldState, String newState) {
		add(newState, oldState);
		if (counter == 0 && newState.equals(Model.GOAL_STATE)) {

			String traceState = newState;
			/*
			 * NOTE: This while loop ran twice, so I added a counter variable
			 * that only ran when it was 0
			 */
			while (traceState != null) {

				pathInverse.push(traceState);
				traceState = history.get(traceState);

			}
			counter++;
			/*
			 * Used the counter variable to tell the user what step they were
			 * on, cold stalwart efficiency
			 */

			String part = pathInverse.pop();
			String firstHalf, secondHalf, thirdHalf;
			firstHalf = part.substring(0, part.length() / 3);
			secondHalf = part.substring(part.length() / 3, (part.length() * 2) / 3);
			thirdHalf = part.substring((part.length() * 2) / 3, part.length());
			if (pathInverse.isEmpty()) {
				System.out.println("You silly goose, it's already solved");
			} else {
				System.out.println("Start ");
				System.out.println("        " + firstHalf);
				System.out.println("        " + secondHalf);
				System.out.println("        " + thirdHalf);
			}

			/*
			 * I split the strings into thirds to make it look like the
			 * gameboard
			 */
			while (!pathInverse.isEmpty()) {
				System.out.println("***");
				part = pathInverse.pop();
				firstHalf = part.substring(0, part.length() / 3);
				secondHalf = part.substring(part.length() / 3, (part.length() * 2) / 3);
				thirdHalf = part.substring((part.length() * 2) / 3, part.length());
				System.out.println("Step " + counter + ". ");
				System.out.println("        " + firstHalf);
				System.out.println("        " + secondHalf);
				System.out.println("        " + thirdHalf);
				counter++;
			}

		}

	}

	/**
	 * creates the up String
	 * 
	 * @param currentState
	 *            currentState being looked at
	 */
	private void up(String currentState) {
		int a = currentState.indexOf("0");
		if (a > 2) {

			String nextState = currentState.substring(0, a - 3) + "0" + currentState.substring(a - 2, a)
					+ currentState.charAt(a - 3) + currentState.substring(a + 1);
			check(currentState, nextState);
		}
	}

	/**
	 * creates the down String
	 * 
	 * @param currentState
	 *            current state being looked at
	 */
	private void down(String currentState) {
		int a = currentState.indexOf("0");
		if (a < 6) {

			String nextState = currentState.substring(0, a) + currentState.substring(a + 3, a + 4)
					+ currentState.substring(a + 1, a + 3) + "0" + currentState.substring(a + 4);
			check(currentState, nextState);
		}
	}

	/**
	 * creates the left string
	 * 
	 * @param currentState
	 *            current State being looked at
	 */
	private void left(String currentState) {
		int a = currentState.indexOf("0");
		if (a != 0 && a != 3 && a != 6) {

			String nextState = currentState.substring(0, a - 1) + "0" + currentState.charAt(a - 1)
					+ currentState.substring(a + 1);
			check(currentState, nextState);
		}
	}

	/**
	 * creates the right string
	 * 
	 * @param currentState
	 *            current State being looked at
	 */
	private void right(String currentState) {
		int a = currentState.indexOf("0");
		if (a != 2 && a != 5 && a != 8) {

			String nextState = currentState.substring(0, a) + currentState.charAt(a + 1) + "0"
					+ currentState.substring(a + 2);
			check(currentState, nextState);
		}
	}

}
