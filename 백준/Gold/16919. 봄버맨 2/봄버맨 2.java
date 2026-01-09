import java.io.*;
import java.util.*;

public class Main {
    static int R, C, N;
    static char[][][] arr;
    static Queue<int[]> bombs = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new char[4][R][C];
        // 0: 초기
        // 1: 1차 폭발 후
        // 2: 2차 폭발 후
        // 3: 폭탄 채운 후
        
        for (char[][] a : arr)
            for (char[] c : a)
                Arrays.fill(c,'O');
        
        int idx = -1;
        if (N % 2 == 0) idx = 3;
    
        if (idx < 0) {
            for (int i=0; i<R; i++) {
                String line = br.readLine();
                for (int j=0; j<C; j++) {
                    char c = line.charAt(j);
                    arr[0][i][j] = c;
                    if (c == 'O') bombs.offer(new int[]{i,j});
                }
            }
    
            if (N == 1) {
                idx = 0;
            } else if (N % 4 == 3) {
                simul1();
                idx = 1;
            } else if (N % 4 == 1) {
                simul1();
                simul2();
                idx = 2;
            }
        }

        for (int i=0; i<R; i++) {
            System.out.println(arr[idx][i]);
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static void simul1() {
        while(!bombs.isEmpty()) {
            int[] cur = bombs.poll();

            int i = cur[0];
            int j = cur[1];

            arr[1][i][j] = '.';

            for (int d=0; d<4; d++) {
                int ni = i + dy[d];
                int nj = j + dx[d];
                if (ni < 0 || ni >= R || nj < 0 || nj >=C) continue;
                arr[1][ni][nj] = '.';
            }
        }
    }
    static void simul2() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                char c = arr[1][i][j];
                if (c == 'O') bombs.offer(new int[]{i,j});
            }
        }

        while(!bombs.isEmpty()) {
            int[] cur = bombs.poll();

            int i = cur[0];
            int j = cur[1];

            arr[2][i][j] = '.';

            for (int d=0; d<4; d++) {
                int ni = i + dy[d];
                int nj = j + dx[d];
                if (ni < 0 || ni >= R || nj < 0 || nj >=C) continue;
                arr[2][ni][nj] = '.';
            }
        }
    }
}