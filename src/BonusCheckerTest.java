import static org.junit.Assert.*;

import org.junit.Test;

public class BonusCheckerTest {

	@Test
	public void test() {
		new BonusChecker();
		assertEquals(new Integer(4), BonusChecker.check(0, 0));
	}

}
