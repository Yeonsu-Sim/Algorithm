import java.util.*;
class Solution {
    public static int subs;
    public static int profit;
    public static int N, M;
    public static int[][] _users;
    public int[] _emoticons;
    
    public int[] solution(int[][] users, int[] emoticons) {
        _users = users;
        _emoticons = emoticons;
        N = emoticons.length;
        M = users.length;
        dfs(0, new int[N]);
        return new int[]{subs, profit};
    }
    
    public void dfs(int depth, int[] arr) {
        if (depth == N) {
            int[] res = calc(arr);
            if (res[0] > subs) {
                subs = res[0];
                profit = res[1];
            } else if (res[0] == subs) {
                if (res[1] > profit) {
                    profit = res[1];
                }
            }
            return;
        }
        for (int i=1; i<=4; i++) {
            arr[depth] = i;
            dfs(depth+1, arr);
        }
    }
    
    public int[] calc(int[] arr) {
        int totalSubs = 0;
        int totalProfit = 0;
        
        for (int j=0; j<M; j++) {
            int[] user = _users[j];
            int min = user[0];
            int budget = user[1];
            
            int sum = 0;
            
            for (int i=0; i<N; i++) {
                if (arr[i]*10 < min) continue;
                sum += (_emoticons[i] - arr[i]*0.1*_emoticons[i]);
            }
            
            if (sum < budget) {
                totalProfit += sum;
            } else {
                totalSubs++;
            }
            
        }
            
        return new int[]{totalSubs, totalProfit};
    }
}