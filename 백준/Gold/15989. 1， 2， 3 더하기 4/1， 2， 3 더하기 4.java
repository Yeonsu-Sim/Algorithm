import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[10001];
        dp[3] = 1;

        for (int i=4; i<=10000; i++) {
            dp[i] = (i-3)/2+1 + dp[i-3];
        }

        int T = parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            int N = parseInt(br.readLine());
                
            if (N < 4) sb.append(N);
            else sb.append((N-3)/2+1 + dp[N-3] + N/2 + 1);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
