import java.io.*;
import java.util.*;

public class Main {
  static int R,C,N, time;
  static int[][] map;
  static int[] dy = {-1,0,1,0};
  static int[] dx = {0,1,0,-1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    map = new int[R][C];

    String line = "";
    for (int i=0; i<R; i++) {
      line = br.readLine();
      for (int j=0; j<C; j++) {
        char c = line.charAt(j);
        if (c == '.') continue;
        map[i][j] = 3;
      }
    }
    
    time = 1;  // 초반 세팅 + 기다리기

    while (time < N) {
      routine();
    }

    for (int i=0; i<R; i++) {
      for (int j=0; j<C; j++) {
        if (map[i][j] > 0) {
          sb.append('O');
        } else {
          sb.append('.');
        }
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }

  static void routine() {
    time++;
    if (time % 2 == 0) install();
    else explore();
  }

  static void install() {
    for (int i=0; i<R; i++) {
      for (int j=0; j<C; j++) {
        if (map[i][j] > 0) continue;
        map[i][j] = time+3;
      }
    }
  }

  static void explore() {
    for (int i=0; i<R; i++) {
      for (int j=0; j<C; j++) {
        if (map[i][j] == time) {
          map[i][j] = 0;
          for (int d=0; d<4; d++) {
            int ni = i + dy[d];
            int nj = j + dx[d];
            if (ni < 0 || ni >= R || nj < 0 || nj >= C) continue;
            if (map[ni][nj] == time) continue;
            map[ni][nj] = 0;
          }
        }
      }
    }
  }

}
