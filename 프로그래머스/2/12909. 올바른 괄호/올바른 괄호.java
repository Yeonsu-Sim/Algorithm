import java.util.*;

class Solution {
    boolean solution(String s) {
        char[] arr = s.toCharArray();
        
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char a : arr) {
            if (!stack.isEmpty()) {
                if (a == ')') {
                    if (stack.peekLast() != '(') break;
                    else {
                        stack.pollLast();
                        continue;
                    }
                }
            }
            
            stack.offerLast(a);
        }

        return stack.size() == 0;
    }
}