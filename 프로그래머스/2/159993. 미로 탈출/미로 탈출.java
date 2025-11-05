import java.util.*;

class Solution {
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
    public int solution(String[] maps) {
        int N = maps.length;
        int M = maps[0].length();
        
        int[] locS = new int[2];
        int[] locL = new int[2];
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (maps[i].charAt(j) == 'S')
                    locS = new int[]{i,j};
                else if (maps[i].charAt(j) == 'L') 
                    locL = new int[]{i,j};
            }
        }
        
        int toL = 0;
        int toE = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        q.offer(new int[]{locS[0], locS[1], 0});
        visited[locS[0]][locS[1]] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int time = cur[2]+1;
            
            for (int d=0; d<4; d++) {
                int ni = cur[0] + dy[d];
                int nj = cur[1] + dx[d];
                
                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if (visited[ni][nj]) continue;
                
                if (maps[ni].charAt(nj) == 'X') continue;
                if (maps[ni].charAt(nj) == 'L') {
                    toL = time;
                    // break;
                }
                
                q.offer(new int[]{ni, nj, time});
                visited[ni][nj] = true;
            }
        }
        if (toL == 0) return -1;
        
        visited = new boolean[N][M];
        q.clear();
        
        q.offer(new int[]{locL[0], locL[1], 0});
        visited[locL[0]][locL[1]] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int time = cur[2]+1;
            
            for (int d=0; d<4; d++) {
                int ni = cur[0] + dy[d];
                int nj = cur[1] + dx[d];
                
                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if (visited[ni][nj]) continue;
                
                if (maps[ni].charAt(nj) == 'X') continue;
                if (maps[ni].charAt(nj) == 'E') {
                    toE = time;
                    // break;
                }
                
                q.offer(new int[]{ni, nj, time});
                visited[ni][nj] = true;
            }
        }
        if (toE == 0) return -1;
        
        return toL + toE;
    }
}