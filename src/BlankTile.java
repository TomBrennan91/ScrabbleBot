import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlankTile extends Tile {

	public BlankTile() {
		super(' ', 0);
		super.icon.setBackground(new Color(0, 120, 0));
	
	}
	
	public BlankTile(int row, int col){
		this();
		super.icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Scrabble.blueTile != null){
					
					System.err.println(row + " " + col + " " + Scrabble.blueTile.getText() );
				}
				
				
			}
		});
	}
	
}
