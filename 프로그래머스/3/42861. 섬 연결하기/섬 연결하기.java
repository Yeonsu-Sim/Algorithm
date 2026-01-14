import java.util.*;

class Solution {
    static int[] parent;
    
    public int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }
    
    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (find(a) == find(b)) return false;
        parent[a] = find(b);
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int size = 0;
        
        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] a, int[] b) -> a[2] - b[2]
        );
        
        for (int[] cost: costs) {
            pq.offer(cost);
        }
        
        boolean[] visited = new boolean[n];
        
        while (size < n-1 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (!union(cur[0], cur[1])) continue;
            size++;
            answer += cur[2];
        }
        
        return answer;
    }
}

// class Node implement Comparable<Node> {
//     int from; int to; int cost;
//     Node(int from, int to, int cost) {
//         this.from = from;
//         this.to = to;
//         this.cost = cost;
//     }
//     @Override
//     public int compare(Node n) {
//         return this.cost - n.cost;
//     }
// }