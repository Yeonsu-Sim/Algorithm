import java.io.*;
import java.util.*;

public class Main {
  static final int LIMIT = 2_000_000;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] dist = new int[N+1][N+1];
    int[][] prev = new int[N+1][N+1];

    for (int[] d : dist) Arrays.fill(d, LIMIT);

    for (int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      if (dist[from][to] > weight) {
        dist[from][to] = weight;
        prev[from][to] = to;
      }

      if (dist[to][from] > weight) {
        dist[to][from] = weight;
        prev[to][from] = from;
      }
    }

    // 최단거리 구하기
    for (int k=1; k<=N; k++) {
      for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
          if (k == i || i == j || k == j) continue;
          int newDist = dist[i][k] + dist[k][j];
          if (dist[i][j] > newDist) {
            dist[i][j] = newDist;
            int tmp = k;
            while(prev[i][tmp] != tmp) {
              tmp = prev[i][tmp];
            }
            prev[i][j] = tmp;
          }
        }
      }
    }

    for (int i=1; i<=N; i++) {
      for (int j=1; j<=N; j++) {
        if (i == j) {
          System.out.print("- ");
          continue;
        }
        System.out.print(prev[i][j]+" ");
      }
      System.out.println();
    }

  }
}