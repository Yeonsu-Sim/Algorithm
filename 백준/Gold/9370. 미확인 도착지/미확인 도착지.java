import java.io.*;
import java.util.*;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int loc, dist;
        public Edge(int loc, int dist) {
            this.loc = loc;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int n, m, t, s, g, h;
    static List<List<Edge>> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            for (int i = 0; i <= n; i++) edges.add(new ArrayList<Edge>());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edges.get(a).add(new Edge(b, d));
                edges.get(b).add(new Edge(a, d));
            }

            int[] distFromS = dijkstra(s);
            int[] distFromG = dijkstra(g);
            int[] distFromH = dijkstra(h);

            List<Integer> candidates = new ArrayList<>();
            while (t-- > 0) {
                int destination = Integer.parseInt(br.readLine());
                // 반드시 g -> h를 지나야 한다는 조건을 만족하는지 체크
                if (distFromS[destination] == distFromS[g] + distFromG[h] + distFromH[destination] ||
                    distFromS[destination] == distFromS[h] + distFromH[g] + distFromG[destination]) {
                    candidates.add(destination);
                }
            }

            Collections.sort(candidates);
            for (int candidate : candidates) {
                sb.append(candidate).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.loc] < cur.dist) continue;

            for (Edge next : edges.get(cur.loc)) {
                int newDist = cur.dist + next.dist;
                if (newDist < dist[next.loc]) {
                    dist[next.loc] = newDist;
                    pq.offer(new Edge(next.loc, newDist));
                }
            }
        }

        return dist;
    }
}