import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t=1; t<=10; t++) {
            
            int N = Integer.parseInt(br.readLine());
            String line = br.readLine();

            Deque<Character> deque = new ArrayDeque<>();
            boolean isVPS = true;
            for (int i=0; i<N; i++) {
                if (!isVPS) break;
                char c = line.charAt(i);

                if (c == ')' || c == ']' || c == '}' || c == '>') {
                    if (c-1 != deque.peekLast() && c-2 != deque.peekLast()) {
                        isVPS = false;
                    } else {
                        deque.pollLast();
                    }
                } else {
                    deque.offerLast(c);
                }
            }

            sb.append('#').append(t).append(' ');
            if (deque.isEmpty() && isVPS) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);
    }
}