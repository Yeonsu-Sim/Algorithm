import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        int[][] dp = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            Arrays.fill(dp[i], 100_000_000);
        }
        
        for (int i=1; i<=n; i++) {
            dp[i][i] = 0;
        }
        
        for (int i=0; i<fares.length; i++) {
            dp[fares[i][0]][fares[i][1]] = fares[i][2];
            dp[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (i == j|| j == k || i == k) continue;
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }
        
        answer = dp[s][a]+dp[s][b];
        for (int k=1; k<=n; k++) {
            int prev = answer;
            answer = Math.min(dp[s][k]+dp[k][a]+dp[k][b], answer);
        }
        
        return answer;
    }
}