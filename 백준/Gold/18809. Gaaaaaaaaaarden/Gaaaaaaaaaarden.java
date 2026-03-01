import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, g, r, pCnt, max;
    private static int[] plantableLand, comb;
    private static int[][] times, colors, dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    private static boolean[][] isLake;
    private static Queue<Integer> queue;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        g = readInt();
        r = readInt();
        
        isLake = new boolean[n][m];
        plantableLand = new int[n*m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = readInt();
                if (v == 0) isLake[i][j] = true;
                else if (v == 2) plantableLand[pCnt++] = i*m+j;
            }
        }
        
        comb = new int[pCnt];
        times = new int[n][m];
        colors = new int[n][m];
        queue = new ArrayDeque<>();
        dfs(0, 0, 0);

        System.out.print(max);
    }
    
    private static void dfs(int depth, int gUsed, int rUsed) {
        if (gUsed > g || rUsed > r) return;
        if (depth == pCnt) {
            if (gUsed == g && rUsed == r) max = Math.max(max, simulate());
            return;
        }
        
        comb[depth] = 0;
        dfs(depth+1, gUsed, rUsed);
        
        comb[depth] = 1;
        dfs(depth+1, gUsed+1, rUsed);
        
        comb[depth] = 2;
        dfs(depth+1, gUsed, rUsed+1);        
    }
    
    private static int simulate() {        
        for (int i = 0; i < n; i++) {
            Arrays.fill(times[i], -1);
            Arrays.fill(colors[i], 0); 
        }
        
        queue.clear();
        for (int i = 0; i < pCnt; i++) {
            if (comb[i] == 0) continue;
            
            int r = plantableLand[i]/m, c = plantableLand[i]%m;
            times[r][c] = 0;
            colors[r][c] = comb[i];
            queue.offer(plantableLand[i]);
        }
        
        int fCnt = 0;        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int r = cur/m, c = cur%m, color = colors[r][c];
            if (color == 3) continue;
            // 칸이 꽃피울수 있는 칸
            // 서로 다른색이 같은턴에 퍼짐
            for (int[] d : dirs) {
                int nr = r+d[0], nc = c+d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || isLake[nr][nc] || colors[nr][nc] == 3) continue;
                if (times[nr][nc] == -1) {
                    times[nr][nc] = times[r][c]+1;
                    colors[nr][nc] = color;
                    queue.offer(nr*m+nc);    
                } else if (times[nr][nc] == times[r][c]+1 && colors[nr][nc] != color && colors[nr][nc] != 3) {
                    colors[nr][nc] = 3;
                    fCnt++;
                }                
            }
        }
        
        return fCnt;
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}