import java.io.*;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[] dp = new int[N+1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    String[] trace = new String[N+1];

    dp[1] = 0;
    trace[1] = "1";
    for (int i=2; i<=N; i++) {
      int num = i-1;
      dp[i] = Math.min(dp[i-1]+1, dp[i]);

      if (i % 3 == 0 && dp[i/3]+1 < dp[i]) {
          num = i/3;
          dp[i] = dp[i/3]+1;
        }
        if (i % 2 == 0 && dp[i/2]+1 < dp[i]) {
          num = i/2;
          dp[i] = dp[i/2]+1;
      }
      trace[i] = i +" "+ trace[num];
    }

    System.out.println(dp[N]);
    System.out.println(trace[N]);
  }
}
