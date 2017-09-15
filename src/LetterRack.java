import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LetterRack implements Constants{
	ArrayList<Tile> tiles;
	JPanel tilePanel;
	Player owner;
	LetterRack(Player owner){
		this.owner = owner;
		tiles = new ArrayList<Tile>();
		refill();

	}


	private void drawTileRack() {
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
		
		
		drawTileRack();
	}
	
	
}
