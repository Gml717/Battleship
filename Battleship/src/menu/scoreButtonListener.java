package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scores.Score;

public class scoreButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Score();
	}

}
