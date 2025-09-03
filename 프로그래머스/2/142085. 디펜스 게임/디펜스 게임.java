import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        while (answer < enemy.length) {
            int target = enemy[answer];
            pq.offer(target);
            
            n -= target;
            
            if (n < 0) {
                if (k == 0) break;
                n += pq.poll();
                k--;
            }
            answer++;
        }
        
        return answer;
    }
        
}