import java.io.*;
import java.util.*;

public class Main {

  static int N, M, K, count;
  static String[] patterns;
  static char[][] map;
  static String pattern;
  static int[][][] dp;
  static int[] dx = {0,1,1,1,0,-1,-1,-1};
  static int[] dy = {-1,-1,0,1,1,1,0,-1};
  static StringBuilder sb = new StringBuilder();
  public static void main(String[] args) throws Exception {
    init();
    for (int i=0; i<K; i++) {
      pattern = patterns[i];
      dp = new int[N][M][pattern.length()];
      for (int[][] first: dp) for (int[] second: first) Arrays.fill(second, -1);
      count = 0;
      match();
    }
    System.out.print(sb);
  }

  static void match() {
    for (int i = 0; i<N; i++) {
      for (int j=0; j<M; j++) {
        count += dfs(0, i,j);
    }
  }
  sb.append(count).append('\n');
}

static int dfs(int depth, int r, int c) {
  if (pattern.charAt(depth) != map[r][c]) return 0;
  if(depth == pattern.length()-1) return 1;
  if (dp[r][c][depth] != -1) return dp[r][c][depth];
  int total = 0;
  for (int d=0; d<8; d++) {
    int ni = (r + dy[d] + N)%N;
    int nj = (c + dx[d] + M)%M;
    total += dfs(depth+1, ni,nj);
  }
  dp[r][c][depth] = total;
  return total;
}

  static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new char[N][M];
    for (int i=0; i<N; i++) {
      String line = br.readLine();
      for (int j=0; j<M; j++) {
        map[i][j] = line.charAt(j);
      }
    }

    patterns = new String[K];
    for (int i=0; i<K; i++) {
      patterns[i] = br.readLine();
    }
  }
}
