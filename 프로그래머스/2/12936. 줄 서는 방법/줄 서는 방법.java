import java.util.*;
class Solution {
    List<Integer> list;
    public int[] solution(int n, long k) {
        list = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            list.add(i);
        }
        
        int[] answer = dfs(0, n, k-1, factorial(n), new int[n]);
        
        return answer;
    }
    
    public int[] dfs(int depth, int n, long k, long left, int[] out) {
        if (depth == n) return out;
        
        left /= (n-depth);
        int idx = (int) (k / left);
        k %= left;
        
        out[depth] = list.get(idx);
        list.remove(idx);
        return dfs(depth+1, n, k, left, out);
    }
    
    public long factorial(long n) {
        if (n == 1) return 1;
        return n * factorial(n-1);
    }
}