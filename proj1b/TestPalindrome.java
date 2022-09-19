import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("level"));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("dog"));
    }

    @Test
    public void testIsPalindromeCC() {
        CharacterComparator ob1 = new OffByOne();
        assertTrue(palindrome.isPalindrome("", ob1));
        assertTrue(palindrome.isPalindrome("a", ob1));
        assertTrue(palindrome.isPalindrome("flake", ob1));
        assertFalse(palindrome.isPalindrome("xyz", ob1));
        assertFalse(palindrome.isPalindrome("aa", ob1));
        assertFalse(palindrome.isPalindrome("zxzx", ob1));

        CharacterComparator obN = new OffByN(3);
        assertTrue(palindrome.isPalindrome("", obN));
        assertTrue(palindrome.isPalindrome("slip", obN));
        assertFalse(palindrome.isPalindrome("aba", obN));
    }


}