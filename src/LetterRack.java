import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LetterRack implements Constants{
	ArrayList<Tile> tiles;
	JPanel display;
	JPanel tilePanel;
	
	LetterRack(){
		tiles = new ArrayList<Tile>();
		refill();
		display = new JPanel();
		display.setBackground(Color.GREEN);
		display.setLayout(new BorderLayout());
		
		tilePanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		for (int i = 0 ; i < TILES_IN_RACK ; i++){
			
			gbc.gridx = i;
			gbc.gridy = 1;
			
			if (i < tiles.size()){
				tilePanel.add(tiles.get(i).icon, gbc);
			} else {
				JButton blank = new JButton("");
				blank.setBackground(Color.WHITE);
				tilePanel.add(blank, gbc);
			}
		}
		display.add(tilePanel, BorderLayout.CENTER);
	}
	
	
	
	public void readTiles(){
		
		for (Tile tile : tiles ){
			System.out.println(tile.toString());
		}
	}
	
	public void refill(){
		while (tiles.size() < TILES_IN_RACK){
			tiles.add(TileBag.getInstance().takeOutTile());
		}
	}
	
	
}
