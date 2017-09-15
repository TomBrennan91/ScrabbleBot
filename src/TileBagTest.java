import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TileBagTest {

	@Test
	public void test() {
		TileBag bag = new TileBag();
		while (!bag.isEmpty()){
			bag.takeOutTile(); //System.out.println());
		}
		assertTrue(true);
	}

}
