import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int[][] dp = new int[n][n];
        
        for (int[] r: results) {
            dp[r[0]-1][r[1]-1] = 1;
            dp[r[1]-1][r[0]-1] = -1;
        }
        
        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (dp[i][k] == 1 && dp[k][j] == 1) {
                        dp[i][j] = 1;
                        dp[j][i] = -1;
                    }
                }
            }
        }
        
        int answer = n;        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i == j) continue;
                if (dp[i][j] == 0) {
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }
}