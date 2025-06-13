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

  static int[] dx = new int[] {-2,-1,-2,-1,1,2,1,2};
  static int[] dy = new int[] {-1,-2,1,2,-2,-1,2,1};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    int T = Integer.parseInt(br.readLine());
    for (int t=1; t<=T; t++) {
      int count = 0;
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[][] dist = new int[N][N];
      for (int[] row : dist) { Arrays.fill(row, -1); }
  
      st = new StringTokenizer(br.readLine());
      int srcR = Integer.parseInt(st.nextToken());
      int srcC = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      int dstR = Integer.parseInt(st.nextToken());
      int dstC = Integer.parseInt(st.nextToken());
      
      ArrayDeque<Pos> q = new ArrayDeque<>();
      q.push(new Pos(srcR,srcC));
      dist[srcR][srcC] = 0;
      while (!q.isEmpty()) {
        Pos cur = q.poll();
        int i = cur.r;
        int j = cur.c;

        if (i == dstR && j == dstC) {
          count = dist[i][j];
          break;
        }

        for (int d=0; d<8; d++) {
          int ni = i + dy[d];
          int nj = j + dx[d];
  
          if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
          if (dist[ni][nj] != -1) continue;

          dist[ni][nj] = dist[i][j] + 1;
          q.offer(new Pos(ni,nj));
        }
      }

      sb.append(count).append('\n');
    }

    System.out.print(sb);
  }
}
