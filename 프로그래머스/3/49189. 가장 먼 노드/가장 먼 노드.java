import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List[] edges = new ArrayList[20001];
        
        for (int i=0; i<20001; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        
        for (int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            edges[a].add(b);
            edges[b].add(a);
        }
        
        boolean[] visited = new boolean[20001];
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1,0});
        visited[1] = true;
        
        while (!q.isEmpty()) {
            answer = q.size();
            // System.out.println("answer: "+answer);
            for (int i=0; i<answer; i++) {
                
                int[] arr = q.poll();
                int cur = arr[0];
                int dist = arr[1];
                
                // System.out.println("cur: "+cur);

                for (int j = 0; j<edges[cur].size(); j++) {
                    int next = (int)edges[cur].get(j);

                    if (visited[next]) continue;
                    visited[next] = true;
                    q.offer(new int[]{next, dist+1});
                }
            }
        }

        return answer;
    }
}