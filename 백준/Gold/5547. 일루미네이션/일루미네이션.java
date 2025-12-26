import java.io.*;
import java.util.*;

public class Main {
  static int[] dy = {-1, 0, 1, 1, 0, -1};
  static int[] odx = {0, 1, 0, -1, -1, -1};
  static int[] edx = {1,1,1,0,-1,0};

  static int N, M, wall;
  static int[][] map;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    visited = new boolean[N][M];

    for (int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }


    for (int j=0; j<M; j++) {
      if (map[0][j] == 1) continue;
      if (visited[0][j]) continue;
      dfs(0, j);
    }
    for (int j=0; j<M; j++) {
      if (map[N-1][j] == 1) continue;
      if (visited[N-1][j]) continue;
      dfs(N-1, j);
    }
    for (int i=0; i<N; i++) {
      if (map[i][0] == 1) continue;
      if (visited[i][0]) continue;
      dfs(i,0);
    }
    for (int i=0; i<N; i++) {
      if (map[i][M-1] == 1) continue;
      if (visited[i][M-1]) continue;
      dfs(i,M-1);
    }

    // 건물 안의 흰색을 검정으로 칠하기
    for (int i=0; i<N; i++) {
      for (int j=0; j<M; j++) {
        if (map[i][j] == 0 && !visited[i][j])
          map[i][j] = 1;
      }
    }

    // 검정색 띠 길이 찾기
    for (int i=0; i<N; i++) {
      for (int j=0; j<M; j++) {
        if (map[i][j] == 0) continue;
        if (visited[i][j]) continue;
        countWall(i,j);
      }
    }

    System.out.println(wall);

  }

  public static void dfs(int r, int c) {
    ArrayDeque<int[]> stack = new ArrayDeque<>();
    stack.offer(new int[]{r,c});
    visited[r][c] = true;

    while (!stack.isEmpty()) {
      int[] cur = stack.poll();
      int i = cur[0];
      int j = cur[1];

      for (int d=0; d<6; d++) {
        int ni = i + dy[d];
        int nj = j + (i % 2 == 0 ? edx[d] : odx[d]);

        if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
        if (map[ni][nj] == 1) continue;
        if (visited[ni][nj]) continue;
        visited[ni][nj] = true;
        stack.offer(new int[]{ni,nj});
      }
    }
  }

  public static void countWall(int r, int c) {
    ArrayDeque<int[]> stack = new ArrayDeque<>();
    stack.offer(new int[]{r,c});
    visited[r][c] = true;

    while (!stack.isEmpty()) {
      int[] cur = stack.poll();
      int i = cur[0];
      int j = cur[1];

      for (int d=0; d<6; d++) {
        int ni = i + dy[d];
        int nj = j + (i % 2 == 0 ? edx[d] : odx[d]);

        if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 0) {
          wall++;
          continue;
        }
        if (visited[ni][nj]) continue;
        visited[ni][nj] = true;
        stack.offer(new int[]{ni,nj});
      }
    }
  }

}