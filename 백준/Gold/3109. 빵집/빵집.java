import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int R, C, pipeCnt;
    static int[] dy = {-1,0,1};  // 오위, 오, 오아래
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] RC = br.readLine().split(" ");

        R = Integer.parseInt(RC[0]);
        C = Integer.parseInt(RC[1]);

        map = new char[R][C];

        String line;
        for (int r=0; r<R; r++) {
            line = br.readLine();
            for (int c=0; c<C; c++) {
                map[r][c] = line.charAt(c);
            }
        }

        for (int r=0; r<R; r++) {
            if(dfs(r, 0)) pipeCnt++;
        }
        System.out.println(pipeCnt);
    }

    static boolean dfs(int row, int col) {
        if (col == C-2) {
            map[row][col] = 'x';
            return true;
        }

        for (int d=0; d<3; d++) {
            int r = row + dy[d];
            int c = col+1;
            if (!isValid(r,c)) continue;
            map[r][c] = 'x';
            if (dfs(r, c)) return true;
            // map[r][c] = '.';  // 철회 할 필요가 없음! -> 연결 안될 건 연결 안됨!
        }
        return false;
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C && map[r][c] != 'x';
    }
}