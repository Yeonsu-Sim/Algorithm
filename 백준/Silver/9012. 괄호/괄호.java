import java.io.*;
import java.util.*;

/**
 * CLUE: 모든 VPS 괄호는 짝꿍을 가진다.
 *  -> 짝꿍을 기다릴 deque를 생성
 *  -> 짝꿍이 온다면 deque에서 pop
 */

public class Main {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int n=0; n<N; n++) {
            String line = br.readLine();
            
            if (isVPS(line)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean isVPS(String str) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '(') {
                deque.offer('(');
            } else {
                if (deque.isEmpty()) return false;  // 빼낼 짝꿍이 없다면? 유감인거지
                deque.pop();
            }
        }
        return deque.isEmpty();
    }
}
