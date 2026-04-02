import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int INF = Integer.MAX_VALUE;
        
        // dp[i][j] = i열 까지의 B의 흔적이 j일 때 A의 최소 흔적
        int[][] dp = new int[info.length+1][m];
        
        for (int[] d : dp) Arrays.fill(d, INF);
        dp[0][0] = 0;
        for (int i=1; i<info.length+1; i++) {
            for (int j=0; j<m; j++) {
                if (dp[i-1][j] == INF) continue;
                
                // A가 훔침
                int clue = dp[i-1][j] + info[i-1][0];
                if (clue < n) dp[i][j] = Math.min(dp[i][j], clue);
                
                // B가 훔침
                clue = j + info[i-1][1];
                if (clue < m) dp[i][clue] = Math.min(dp[i][clue], dp[i-1][j]);
            }
        }
        
        int answer = INF;
        for (int j=0; j<m; j++) {
            answer = Math.min(answer, dp[info.length][j]);
        }
        
        return answer == INF ? -1 : answer;
    }
}