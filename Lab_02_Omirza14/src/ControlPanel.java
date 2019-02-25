import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * 
 * @author omer
 *
 *         This panel will contain the controls for the game (the undo button,
 *         the shuffle button)
 */
public class ControlPanel extends JPanel {
	PuzzleController c;
	JButton shuffleButton, undoButton;
	JButton btnSolve;

	public ControlPanel(PuzzleController c) {
		super();
		this.c = c;
		this.setPreferredSize(new Dimension(150, 450));
		this.setBackground(Color.white);
		setLayout(new BorderLayout(0, 0));

		JPanel ButtonPanel = new JPanel();
		add(ButtonPanel);
		GridBagLayout gbl_ButtonPanel = new GridBagLayout();
		gbl_ButtonPanel.columnWidths = new int[] { 0, 0 };
		gbl_ButtonPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_ButtonPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_ButtonPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		ButtonPanel.setLayout(gbl_ButtonPanel);

		JPanel shufflePanel = new JPanel();
		GridBagConstraints gbc_shufflePanel = new GridBagConstraints();
		gbc_shufflePanel.insets = new Insets(0, 0, 5, 0);
		gbc_shufflePanel.fill = GridBagConstraints.BOTH;
		gbc_shufflePanel.gridx = 0;
		gbc_shufflePanel.gridy = 0;
		ButtonPanel.add(shufflePanel, gbc_shufflePanel);
		shufflePanel.setLayout(new BorderLayout(0, 0));

		shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(c);
		shufflePanel.add(shuffleButton, BorderLayout.CENTER);

		JPanel undoPanel = new JPanel();
		GridBagConstraints gbc_undoPanel = new GridBagConstraints();
		gbc_undoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_undoPanel.fill = GridBagConstraints.BOTH;
		gbc_undoPanel.gridx = 0;
		gbc_undoPanel.gridy = 1;
		ButtonPanel.add(undoPanel, gbc_undoPanel);
		undoPanel.setLayout(new BorderLayout(0, 0));

		undoButton = new JButton("Undo");
		undoButton.addActionListener(c);
		undoPanel.add(undoButton, BorderLayout.CENTER);

		JPanel solvePanel = new JPanel();
		GridBagConstraints gbc_solvePanel = new GridBagConstraints();
		gbc_solvePanel.fill = GridBagConstraints.BOTH;
		gbc_solvePanel.gridx = 0;
		gbc_solvePanel.gridy = 2;
		ButtonPanel.add(solvePanel, gbc_solvePanel);
		solvePanel.setLayout(new BorderLayout(0, 0));

		btnSolve = new JButton("Solve");
		btnSolve.addActionListener(c);
		solvePanel.add(btnSolve, BorderLayout.CENTER);
		// shuffle = new JButton("Shuffle");
		// undo = new JButton("Undo");
		// TODO Auto-generated constructor stub
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public JButton getShuffleButton() {
		return this.shuffleButton;
	}

	public JButton getUndoButton() {
		return this.undoButton;
	}

	public JButton getSolveButton() {
		return this.btnSolve;
	}
}
