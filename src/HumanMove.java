import java.util.ArrayList;

import javax.swing.JOptionPane;

public class HumanMove{
	
	private static ArrayList<HumanAction> actionList;
	static{
		actionList = new ArrayList<HumanAction>();
	}
	
	public static ArrayList<HumanAction> getInstance(){
		return  actionList;
	}
	
	public static boolean isValid(){
		return hasMovedTiles() && (isRow() || isCol() ) ;
	}
	
	private static boolean hasMovedTiles(){
		if (actionList.size() > 0){
			return true;
		}
		JOptionPane.showMessageDialog(null, "Please move some tiles onto the board before pressing 'Play'");
		return false;
	}
	
	private static boolean isRow(){
		return true;
	}
	private static boolean isCol(){
		return true;
	}
	
	
	
}


class HumanAction{
	public HumanAction(Tile movedTile, int row, int col) {
		this.movedTile = movedTile;
		this.row = row;
		this.col = col;
	}
	@Override
	public String toString() {
		return "HumanAction [movedTile=" + movedTile + ", row=" + row + ", col=" + col + "]";
	}
	Tile movedTile;
	int row;
	int col;
}
