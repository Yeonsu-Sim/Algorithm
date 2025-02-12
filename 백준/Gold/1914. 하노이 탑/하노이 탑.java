import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Stack 자료구조 이용 보다는, 구색만 맞추면 되는 문제!
 */
public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        BigInteger count = new BigInteger("2");
        //주의!!) n이 100인 경우 long의 범위를 넘어간다
        System.out.println(count.pow(N).subtract(new BigInteger("1")));

        if (N > 20) {  // N이 20보다 큰 경우에는 출력할 필요 없음 (문제 조건)
            return;
        }
        move(N, 1, 3, 2);
        System.out.println(sb);
    }

    // num을 목적지에 쌓기
    static void move(int depth, int from, int to, int temp) {
        if (depth == 0) {
            return;
        }
        
        // num-1을 
        move(depth-1, from, temp, to);
        sb.append(from).append(' ').append(to).append('\n');
        move(depth-1, temp, to, from);
    }
}
