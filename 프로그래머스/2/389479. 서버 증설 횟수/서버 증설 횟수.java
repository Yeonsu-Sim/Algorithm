import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int h=0; h<24; h++) {
            while (!q.isEmpty() && q.peek() == 0) q.poll();
            
            while (players[h] - m*q.size() >= m) {
                q.offer(k);
                answer++;
            }
            
            int size = q.size();
            while (size-- > 0) {
                int time = q.poll();
                q.offer(time-1);
            }
        }
        
        return answer;
    }
}