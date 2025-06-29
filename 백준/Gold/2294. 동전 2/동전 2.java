import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] coins = new int[N];
    int[] dp = new int[K+1];
    int MAX  = 987654321;
    Arrays.fill(dp, MAX);

    for (int i=0; i<N; i++) {
      int coin = Integer.parseInt(br.readLine());
      coins[i] = coin;
      if (coin > K) continue;
      dp[coin] = 1;
    }

    for (int i=1; i<=K; i++) {
      for (int j=0; j<N; j++) {
        int coin = coins[j];
        if (i - coin <= 0) continue;
        dp[i] = Math.min(dp[i], dp[i-coin]+1);
      }
    }

    System.out.println(dp[K] == MAX ? -1 : dp[K]);
  }
}
