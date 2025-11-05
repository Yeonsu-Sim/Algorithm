import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = 
            new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i=0; i<works.length; i++) {
            pq.offer(works[i]);
        }
        
        for (int i=0; i<n; i++) {
            // if (pq.isEmpty()) break;
            
            int cur = pq.poll();
            // if (cur == 1) continue;
            if (cur == 0) break;
            pq.offer(cur-1);
        }
        
        long answer = 0;
        for (int q : pq) answer += Math.pow(q,2);
        
        return answer;
    }
}