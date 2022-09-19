public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new ArrayDeque<Character> ();
        for (int i=0; i<word.length(); i++){
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        return PalindromeHelper(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        return PalindromeHelper(wordDeque, cc);
    }

    private static boolean PalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc){
        if (wordDeque.size() <= 1){
            return true;
        }
        return (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast()) && PalindromeHelper(wordDeque, cc));
    }

    private static boolean PalindromeHelper(Deque<Character> wordDeque){
        if (wordDeque.size() <= 1){
            return true;
        }
        return ((wordDeque.removeFirst() == wordDeque.removeLast()) && PalindromeHelper(wordDeque));
    }
}
