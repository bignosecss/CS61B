public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> ad = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i+=1) {
            // Get the i-th character in word
            // then add to the last of the ArrayDeque
            ad.addLast(word.charAt(i));
        }
        return ad;
    }

    // Implement recursively without using the Deque data structure
//    public boolean isPalindrome(String word) {
//        return isPalindrome(word, 0, word.length() - 1);
//    }
//    private boolean isPalindrome(String word, int start, int end) {
//        // Any word of length 0 or 1 is a palindrome
//        if (start >= end) {
//            return true;
//        }
//        // If the char in the start and end is not equal
//        // then the word is not a palindrome
//        if (word.charAt(start) != word.charAt(end)) {
//            return false;
//        }
//        return isPalindrome(word, start + 1, end - 1);
//    }
    // Using the Deque data structure
    public boolean isPalindrome(String word) {
        Deque<Character> dq = wordToDeque(word);
        return isPalindrome(dq);
    }
    private boolean isPalindrome(Deque<Character> dq) {
        // If the size of the deque is 0 or 1, then it's a palindrome
        if (dq.size() <= 1) {
            return true;
        }

        // Get the first and last item
        // If they are not equal, then it's not a palindrome
        char first = dq.removeFirst();
        char last = dq.removeLast();
        if (first != last) {
            return false;
        }

        // Since the first and last item have been removed,
        // we only need to check if the rest deque is palindrome
        return isPalindrome(dq);
    }

    // Overloading of isPalindrome that has different
    // amount of parameters
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dq = wordToDeque(word);
        return isPalindromeOBO(dq, cc);
    }
    private boolean isPalindromeOBO(Deque<Character> dq, CharacterComparator cc) {
        if (dq.size() <= 1) {
            return true;
        }

        char first = dq.removeFirst();
        char last = dq.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }

        return isPalindromeOBO(dq, cc);
    }
}
