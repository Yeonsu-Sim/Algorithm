import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dp = new int[1001][1001];
            dp[1][1] = 1;
            dp[2][1] = 1;
            dp[3][1] = 1;

            for (int i=1; i<=N; i++) {
                for (int j=2; j<=i; j++) {

                    if (i-1 > 0) dp[i][j] += dp[i-1][j-1];
                    dp[i][j] %= MOD;
                    if (i-2 > 0) dp[i][j] += dp[i-2][j-1];
                    dp[i][j] %= MOD;
                    if (i-3 > 0) dp[i][j] += dp[i-3][j-1];
                    dp[i][j] %= MOD;
                }
            }

            long sum = 0;
            for (int j=1; j<=M; j++) {
                sum += dp[N][j];
                sum %= MOD;
            }
            sb.append(sum).append('\n');
        }

        System.out.println(sb);
    }
}