import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int N, E;
    static List<Edge>[] graph;

    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int[] dijkstra(int src) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        dist[src] = 0;
        pq.offer(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.cost > dist[cur.to]) continue;
            for (Edge e : graph[cur.to]) {
                int nd = cur.cost + e.cost;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.offer(new Edge(e.to, nd));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, c));
            graph[v].add(new Edge(u, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] d1  = dijkstra(1);
        int[] dV1 = dijkstra(v1);
        int[] dV2 = dijkstra(v2);

        long path1 = (long)d1[v1] + dV1[v2] + dV2[N];
        long path2 = (long)d1[v2] + dV2[v1] + dV1[N];
        long ans   = Math.min(path1, path2);

        System.out.println(ans >= INF ? -1 : ans);
    }
}
