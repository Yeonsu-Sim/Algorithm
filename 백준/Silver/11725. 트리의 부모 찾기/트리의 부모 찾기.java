import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;

    List<Integer>[] graph = new ArrayList[N+1];
    for (int i=1; i<=N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i=1; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      graph[from].add(to);
      graph[to].add(from);
    }

    int[] parents = new int[N+1];

    Queue<Integer> q = new ArrayDeque<>();
    q.offer(1);
    parents[1] = -1;

    while (!q.isEmpty()) {
      int cur = q.poll();
      for (int child : graph[cur]) {
        if (parents[child] != 0) continue;
        parents[child] = cur;
        q.offer(child);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i=2; i<=N; i++) {
      sb.append(parents[i]).append('\n');
    }

    System.out.println(sb);
  }
}
