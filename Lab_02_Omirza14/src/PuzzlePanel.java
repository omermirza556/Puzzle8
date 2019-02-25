
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author omer
 *
 *         This class is an extension of the panel class. The goal of this class
 *         is to create a panel that draws on a game board.
 */
public class PuzzlePanel extends JPanel {

	PuzzleController c;
	Tile[][] board;

	public PuzzlePanel(PuzzleController c) {
		super();
		board = c.myModel.getGameBoard();
		this.c = c;
		this.setPreferredSize(new Dimension(450, 450));
		this.setBackground(Color.white);
		this.addMouseListener(c);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < c.myModel.xLength; i++) {
			for (int k = 0; k < c.myModel.yLength; k++) {
				if (!(board[i][k].getNumber().equals("0"))) {
					board[i][k].drawOn(g);
				}

			}
		}

	}


	public void shuffle(Tile[][] board) {
		// TODO: Shuffle the board to make it legal
	}

	public PuzzleController returnController() {
		return c;
	}

}
