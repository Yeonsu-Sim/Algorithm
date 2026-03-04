import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> edge = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            edge.add(new ArrayList<>());
        }
        
        for (int[] r: roads) {
            edge.get(r[0]).add(r[1]);
            edge.get(r[1]).add(r[0]);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        dist[destination] = 0;
        
        int d = 0;
        while (!q.isEmpty()) {
            d++;
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();

                for (int next : edge.get(cur)) {
                    if (dist[next] >= 0) continue;
                    dist[next] = d;
                    q.offer(next);
                }
            }        
        }
        
        int[] answer = new int[sources.length];
        int idx = 0;
        for (int s : sources) {
            answer[idx++] = dist[s];
        }
        
        return answer;
    }
}