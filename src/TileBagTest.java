import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TileBagTest {

	@Test
	public void test() {
		TileBag bag = new TileBag();
		while (!bag.isEmpty()){
			System.out.println(bag.takeOutTile());
		}
		assertTrue(true);
	}

}
