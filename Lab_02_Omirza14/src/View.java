import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author omer
 * 
 * 
 *         This class displays the content
 */
public class View {
	JFrame mainFrame;
	JPanel mainPanel;
	JPanel winPanel;
	// ControlPanel cPanel;
	PuzzlePanel pPanel;
	ControlPanel cPanel;
	PuzzleController c;

	public View(PuzzleController c) {
		this.c = c;
		mainFrame = new JFrame("Puzzle 8");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(800, 500));
		pPanel = new PuzzlePanel(c);
		cPanel = new ControlPanel(c);
		winPanel = new JPanel();
		winPanel.setPreferredSize(new Dimension(800, 500));
		winPanel.setBackground(Color.green);
		winPanel.add(new JLabel("YOU WIN"));
		winPanel.setVisible(false);
		mainPanel.setBackground(Color.gray);
		mainPanel.add(pPanel);
		mainPanel.add(cPanel);
		mainPanel.add(winPanel);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);

		// TODO Auto-generated constructor stub
	}

	public void repaint() {
		pPanel.repaint();
	}

	public ControlPanel getControlPanel() {
		return this.cPanel;
	}

}
