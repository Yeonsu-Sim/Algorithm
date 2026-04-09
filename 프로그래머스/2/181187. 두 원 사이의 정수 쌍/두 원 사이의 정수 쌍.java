import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        int INF = 1_000_001;
        int[] dp = new int[INF];

        // 큰 원의 내부 점 개수
        dp[r2] = 0;
        int h = r2-1;
        for (int i=1; i<=r2; i++) {
            while (r2*r2 < i*i + h*h) h--;
            dp[i] = h;
            dp[h] = i;
        }
        
        long r2Sum = 0;
        for (int i=1; i<=r2; i++) r2Sum += (dp[i]+1);

        Arrays.fill(dp, 0);
        
        // 작은 원의 내부 점 개수
        dp[r1] = 0;
        h = r1-1;
        long onTheLineCnt = 0;
        for (int i=1; i<=r1; i++) {
            
            while (r1*r1 < i*i + h*h) h--;
            dp[i] = h;
            dp[h] = i;
            if (i*i + h*h == r1*r1) onTheLineCnt++;
        }
        
        long r1Sum = 0;
        for (int i=1; i<=r1; i++) r1Sum += (dp[i]+1);
        
        
        System.out.println(r2Sum+" "+r1Sum + " "+ onTheLineCnt);
        return (r2Sum-r1Sum+onTheLineCnt)*4;
    }
}