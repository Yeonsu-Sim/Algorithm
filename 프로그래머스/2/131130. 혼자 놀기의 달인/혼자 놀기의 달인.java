import java.util.*;

class Solution {
    public int solution(int[] cards) {
        
        int a = 0;
        int b = 0;
        
        int N = cards.length;
        
        boolean[] visited = new boolean[N];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            Collections.reverseOrder()
        );
        
        for (int i=0; i<N; i++) {
            int cur = i;
            
            if (visited[cur]) continue;
            visited[cur] = true;
            int cnt = 1;
            
            int next = cards[cur] -1;
            while (next != i) {
                visited[next] = true;
                cnt++;
                cur = next;
                next = cards[cur] -1;
            }
            
            // if (a < cnt) a = cnt;
            // else if (b < cnt) b = cnt;
            pq.offer(cnt);
            
        }
        
        if (pq.size() < 2) return 0;
        
        return pq.poll() * pq.poll();
    }
}