import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {
	private Tile[][] gameBoard = new Tile[3][3];
	public int x, y;

	@Test
	public void test() {

		Graph graph = new Graph(gameBoard);
		//

		// Node theNode = new Node(gameBoard);
		// // System.out.println(theNode.getCurrentState());
		// Node rightNode = new Node(Direction.RIGHT,
		// theNode.s.makeRightChild());
		// Node leftNode = new Node(Direction.LEFT, theNode.s.makeLeftChild());
		// Node upNode = new Node(Direction.UP, theNode.s.makeTopChild());
		// Node downNode = new Node(Direction.DOWN, theNode.s.makeBotChild());
		//
		// assertTrue(rightNode.s != null);
		// System.out.println(rightNode.getCurrentState());
		// assertTrue(leftNode.s == null);
		// // assertTrue(leftNode == null);
		// assertTrue(upNode.s == null);
		// // assertTrue(upNode == null);
		// assertTrue(downNode.s != null);
		// System.out.println(downNode.getCurrentState());
		//
		// Graph graph = new Graph(rightNode);
		// graph.search();
		// // Node node = graph.search();
		// // System.out.println(node.getCurrentState());

		System.out.println(0 % 4);
		System.out.println(1 % 4);
		System.out.println(2 % 4);
		System.out.println(3 % 4);

	}

}
