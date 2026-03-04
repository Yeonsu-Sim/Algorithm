import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int t : tangerine) {
            Integer cnt = map.get(t);
            
            if (cnt == null) map.put(t,1);
            else map.put(t, cnt+1);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] a, int[] b) -> b[1] - a[1]
        );
        
        for (int key : map.keySet()) {
            pq.offer(new int[]{key, map.get(key)});
        }
        
        
        while (k > 0) {
            int[] cur = pq.poll();
            k -= cur[1];
            answer++;
        }
        
        return answer;
    }
}