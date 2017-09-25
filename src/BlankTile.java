import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BlankTile extends Tile {

	boolean empty;
	
	public BlankTile() {
		super(' ', 0);
		super.icon.setBackground(new Color(0, 120, 0));
		empty = true;
	}
	
	public BlankTile(int row, int col){
		this();
		JButton icon = super.icon;
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Scrabble.blueTile != null && empty){
					Scrabble.user.letterRack.tilePanel.remove(Scrabble.blueTile.icon);
					icon.setText(Scrabble.blueTile.icon.getText());
					Scrabble.blueTile.setRed();
					icon.setIcon(Scrabble.blueTile.icon.getIcon());
					Scrabble.user.redrawRack();
					HumanMove.getInstance().add(new HumanAction(Scrabble.blueTile, row, col));
					Scrabble.blueTile = null;
					empty = false;
				}
			}
		});
	}
	
}
