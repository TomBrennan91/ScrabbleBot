import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BlankTile extends Tile {

	public BlankTile() {
		super(' ', 0);
		super.icon.setBackground(new Color(0, 120, 0));
	
	}
	
	public BlankTile(int row, int col){
		this();
		JButton icon = super.icon;
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Scrabble.blueTile != null){
					System.err.println(row + " " + col + " " + Scrabble.blueTile.getText() );
					icon.setText(Scrabble.blueTile.getText());
					icon.setBackground(Color.ORANGE);
					Scrabble.user.letterRack.tilePanel.remove(Scrabble.blueTile); //remove(Scrabble.blueTile);
					Scrabble.user.redrawRack();
					Scrabble.blueTile = null;
				}
			}
		});
	}
	
}
