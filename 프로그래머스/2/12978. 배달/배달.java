import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        List<List<int[]>> edges = new ArrayList<>();
        for (int i=0; i<=N; i++) edges.add(new ArrayList<>());
        for (int[] r : road) {
            edges.get(r[0]).add(new int[]{r[1], r[2]});
            edges.get(r[1]).add(new int[]{r[0], r[2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] a, int[] b) -> a[1] - b[1]
        );
        
        pq.offer(new int[]{1,0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (dp[cur[0]] <= cur[1]) continue;
            dp[cur[0]] = cur[1];
            
            for (int[] next : edges.get(cur[0])) {
                if (dp[next[0]] <= cur[1] + next[1]) continue;
                pq.offer(new int[]{next[0], cur[1]+next[1]});
            }
        }
        
        for (int i=1; i<=N; i++) {
            if (dp[i] <= K) answer++;
        }
        
        return answer;
    }
}