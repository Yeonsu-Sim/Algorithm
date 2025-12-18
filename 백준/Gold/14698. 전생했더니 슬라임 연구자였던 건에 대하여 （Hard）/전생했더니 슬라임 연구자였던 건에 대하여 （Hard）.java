import java.io.*;
import java.util.*;

public class Main {
  static final int MOD = 1_000_000_007;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      PriorityQueue<Long> pq = new PriorityQueue<>();
      for (int i=0; i<N; i++) {
        pq.offer(Long.parseLong(st.nextToken()));
      }

      if (N == 1) {
        sb.append(1).append('\n');
        continue;
      }

      long answer = 1;
      while (pq.size() > 1) {
        long a = pq.poll();
        long b = pq.poll();

        long mul = a*b;

        answer *= mul % MOD;
        answer %= MOD;

        pq.offer(mul);
      }

      sb.append(answer).append('\n');
    }

    System.out.println(sb);
  }
}