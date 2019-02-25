import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author omer
 * 
 *         This class is for a singular tile object that will contain a String
 *         of a number that will be used to determine a goal state
 */
public class Tile {
	private int x, y;
	private String number;
	private int size = 150;
	public static final int side = 150;
	Random rand = new Random();
	private Color c;
	private Tile left, right, up, down;

	public Tile(int x, int y, String number) {
		this.x = x * size;
		this.y = y * size;
		this.number = number;
		c = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

	}

	public void drawOn(Graphics g) { // Draws the object
		g.setColor(c);
		//g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		g.fillRect(x, y, size, size);
		g.setColor(Color.white);
		//g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		g.drawString(number, x + (size / 2), y + (size / 2));
		g.drawRect(x, y, size, size);

	}

	/**
	 * returns true if the the tile is next to zero and returns false if the
	 * tile is not
	 * 
	 * @return if the tile is next to zero
	 * 
	 * 
	 */
	public boolean isNextToZero() {
		if (this.left != null && this.left.getNumber().equals("0")) {
			return true;
		}
		if (this.right != null && this.right.getNumber().equals("0")) {
			return true;
		}
		if (this.up != null && this.up.getNumber().equals("0")) {
			return true;
		}
		if (this.down != null && this.down.getNumber().equals("0")) {
			return true;
		}

		return false;

	}

	/**
	 * if the tile is next to zero it will return where the zero tile is in
	 * proximety to the current tile
	 * 
	 * @return Direction of the zero square
	 * 
	 */
	public Direction whereIsZero() {
		if (this.left != null && this.left.getNumber().equals("0")) {
			return Direction.LEFT;
		}
		if (this.right != null && this.right.getNumber().equals("0")) {
			return Direction.RIGHT;
		}
		if (this.up != null && this.up.getNumber().equals("0")) {
			return Direction.UP;
		}
		if (this.down != null && this.down.getNumber().equals("0")) {
			return Direction.DOWN;
		}

		return null;
	}

	/**
	 * Changes the x and y coordinate of the tile
	 * 
	 * @param x
	 * @param y
	 */
	public void changeCoor(int x, int y) {
		this.x = x;
		this.y = y;

	}

	/*
	 * Generated getters and setters
	 */

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public Tile getLeft() {
		return left;
	}

	public void setLeft(Tile left) {
		this.left = left;
	}

	public Tile getRight() {
		return right;
	}

	public void setRight(Tile right) {
		this.right = right;
	}

	public Tile getUp() {
		return up;
	}

	public void setUp(Tile up) {
		this.up = up;
	}

	public Tile getDown() {
		return down;
	}

	public void setDown(Tile down) {
		this.down = down;
	}

}
