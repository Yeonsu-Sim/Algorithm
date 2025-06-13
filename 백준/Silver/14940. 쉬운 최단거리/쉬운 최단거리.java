import java.io.*;
import java.util.*;

public class Main {
  static class Pos {
    int r, c;
    Pos(int r, int c) {
      this.r = r;
      this.c = c;
    }
    @Override
    public String toString() {
      return "Pos [r=" + r + ", c=" + c + "]";
    }
  }

  static int[] dx = new int[] {-1,1,0,0};
  static int[] dy = new int[] {0,0,-1,1};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] dist = new int[N][M];
    for (int[] row : dist) { Arrays.fill(row, -1); }

    int destR = 0, destC = 0;
    
    for (int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<M; j++) {
        int num = Integer.parseInt(st.nextToken());
        if (num == 0) {
          dist[i][j] = 0;
        } else if (num == 2) {
          destR = i; destC = j;
        }
      }
    }
    
    ArrayDeque<Pos> q = new ArrayDeque<>();
    q.push(new Pos(destR,destC));
    dist[destR][destC] = 0;
    while (!q.isEmpty()) {
      Pos cur = q.poll();

      int i = cur.r;
      int j = cur.c;
      for (int d=0; d<4; d++) {
        int ni = i + dy[d];
        int nj = j + dx[d];

        if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
        if (dist[ni][nj] >= 0) continue;

        dist[ni][nj] = dist[i][j] + 1;
        q.offer(new Pos(ni,nj));
      }
    }

    for (int i=0; i<N; i++) {
      for (int j=0; j<M; j++) {
        sb.append(dist[i][j]).append(' ');
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }
}
