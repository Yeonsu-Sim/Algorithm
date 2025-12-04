import java.io.*;
import java.util.*;

public class Main {
  static int N, M, H;
  static int[] dy = {-1,0,1,0,0,0};
  static int[] dx = {0,1,0,-1,0,0};
  static int[] dz = {0,0,0,0,-1,1};
  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    int[][][] box = new int[H][N][M];
    Queue<int[]> q = new ArrayDeque<>();

    for (int h=0; h<H; h++) {
      for (int n=0; n<N; n++) {
        st = new StringTokenizer(br.readLine());
        for (int m=0; m<M; m++) {
          int tomato = Integer.parseInt(st.nextToken());
          box[h][n][m] = tomato;
          if (tomato == 1) {
            q.offer(new int[]{h,n,m});
          }
        }
      }
    }

    int time = 0;

    while (!isEnd(box)) {
      if (q.isEmpty()) {
        System.out.println("-1");
        return;
      }

      int s = q.size();
      while (s-- > 0) {
        int[] cur = q.poll();
        int h = cur[0], i =cur[1], j = cur[2];
  
        for (int d=0; d<6; d++) {
          int nh = h + dz[d];
          int ni = i + dy[d];
          int nj = j + dx[d];

          if (!isIn(nh, ni, nj)) continue;

          if (box[nh][ni][nj] == 0) {
            box[nh][ni][nj] = 1;
            q.offer(new int[]{nh,ni,nj});
          }
        }
      }
      time++;

    }

    System.out.println(time);
  }


  public static boolean isIn(int h, int i, int j) {
    if (i < 0 || i >= N || j < 0 || j >= M || h < 0 || h >= H) return false;
    return true;
  }

  public static boolean isEnd(int[][][] arr) {
    for (int h=0; h<H; h++) {
      for (int n=0; n<N; n++) {
        for (int m=0; m<M; m++) {
          if (arr[h][n][m] == 0) return false;
        }
      }
    }
    return true;
  }
}
