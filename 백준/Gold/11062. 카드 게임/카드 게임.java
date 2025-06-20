import java.io.*;
import java.util.*;

public class Main {
  static int T, N;
  static int[][] dp;
  static int[] acc;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    T = Integer.parseInt(br.readLine());
    for (int t=0; t<T; t++) {
      N = Integer.parseInt(br.readLine());
      dp = new int[N][N];
      acc = new int[N+1];

      st = new StringTokenizer(br.readLine());
      for (int i=0; i<N; i++) {
        dp[i][i] = Integer.parseInt(st.nextToken());
        acc[i+1] = acc[i] + dp[i][i];
      }

      for (int size=2; size<=N; size++) {
        for (int i=0; i<N-size+1; i++) {
          int j = i+size -1;
          dp[i][j] = acc[j+1] - acc[i] - Math.min(dp[i+1][j], dp[i][j-1]);
        }
      }
      sb.append(dp[0][N-1]).append('\n');
    }
    System.out.print(sb);
  }
}
