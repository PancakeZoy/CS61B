import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestOffByN {

    @Test
    public void testEqualChars(){
        OffByN offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a','f'));
        assertTrue(offBy5.equalChars('f','a'));
        assertFalse(offBy5.equalChars('a','b'));
        assertFalse(offBy5.equalChars('a','a'));
    }
}
