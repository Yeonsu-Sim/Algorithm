import java.io.*;
import java.util.*;

class Solution {
    static ArrayDeque<int[]> stack = new ArrayDeque<>();
    static int[][] arr;
    static int[] dy = {0,0,-1,1};
    static int[] dx = {-1,1,0,0};
    static int R, C;
    static int[] counts;
    static boolean[][] visited;
    public int solution(int[][] land) {
        
        R = land.length;
        C = land[0].length;
        arr = land;
        counts = new int[C];
        visited = new boolean[R][C];
        
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                if (arr[i][j] == 0) continue;
                bfs(i,j);
            }
        }
        
        Arrays.sort(counts);
        int answer = counts[C-1];
        return answer;
    }
    
    static void bfs(int i, int j) {
        Set<Integer> set = new HashSet<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i,j});
        set.add(j);
        int count = 1;
        
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            
            for (int d=0; d<4; d++) {
                int ni = pos[0] + dy[d];
                int nj = pos[1] + dx[d];
                if (ni < 0 || ni >= R || nj < 0 || nj >=C) continue;
                
                if (visited[ni][nj]) continue;                
                visited[ni][nj] = true;
                if (arr[ni][nj] == 0) continue;
                count++;
                q.offer(new int[]{ni,nj});
                set.add(nj);
            }
        }
        
        for (int s : set) {
            counts[s] += count;
        }
    }
}