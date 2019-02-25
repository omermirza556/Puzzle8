import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.Timer;

/**
 * 
 * @author omer
 * 
 * 
 *         This class creates a buffer between the view and the model to adhere
 *         to the mvc concept
 *         
 *         NOTE: MAIN METHOD IS IN THIS CLASS
 * 
 * 
 */
public class PuzzleController implements ActionListener, MouseListener {

	public Model myModel;
	public View myView;
	File errorSound, AISound;
	AudioClip errorClip, AIClip;
	Timer t;

	public PuzzleController() {
		myModel = new Model();
		myView = new View(this);
		t = new Timer(100, this);
		t.start();

		try {
			errorSound = new File("0477.aiff");
			errorClip = Applet.newAudioClip(errorSound.toURI().toURL());
		} catch (MalformedURLException e) {
			System.err.println("Invalid file");
			errorSound = null;
			errorClip = null;
		}

		try {
			AISound = new File("X-Files-short-version.aiff");
			AIClip = Applet.newAudioClip(AISound.toURI().toURL());
		} catch (MalformedURLException e) {
			System.err.println("Invalid file");
			AISound = null;
			AIClip = null;
		}
	}

	public void addToStack(Motion move) {
		myModel.getMoves().add(move);
	}

	public Motion removeFromStack() {
		return myModel.getMoves().pop();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!myModel.click(e.getX(), e.getY())) {
			/*
			 * Plays the wilhelm scream if the user tries an invalid move
			 */
			System.out.println("INVALID MOVE");
			errorClip.play();
		}
		myView.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (myModel.currentState().equals(Model.GOAL_STATE)) {
			myView.winPanel.setVisible(true);
		} else {
			myView.winPanel.setVisible(false);
		}
		if (e.getSource() == myView.getControlPanel().getUndoButton()) {

			/*
			 * Only undos up til the original shuffle, will not go further than
			 * that
			 */
			if (myModel.moves.size() > myModel.shuffleMoves) {
				myModel.undo();
			}

		}

		if (e.getSource() == myView.getControlPanel().getShuffleButton()) {
			myModel.shuffle();
		}

		if (e.getSource() == myView.getControlPanel().getSolveButton()) {
			/*
			 * plays the xfiles theme whenever the AI button is clicked
			 */
			AIClip.play();
			myModel.AI();
		}

		myView.repaint();

	}

	public static void main(String[] args) {

		new PuzzleController();

	}

}
