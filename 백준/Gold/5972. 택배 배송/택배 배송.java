import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static List<List<int[]>> edges = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) edges.add(new ArrayList<>());

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) -1;
            int to = Integer.parseInt(st.nextToken()) -1;
            int cost = Integer.parseInt(st.nextToken());

            edges.get(from).add(new int[]{to, cost});
            edges.get(to).add(new int[]{from, cost});
        }

        System.out.println(dijkstra(0)[N-1]);
    }

    public static int[] dijkstra(int start) {
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] a, int[] b) -> a[1] - b[1]
        );

        pq.offer(new int[]{start,0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int c = cur[1];

            if (dp[v] <= c) continue;
            dp[v] = c;

            for (int[] next : edges.get(v)) {
                if (dp[next[0]] <= c + next[1]) continue;
                pq.offer(new int[]{next[0], c + next[1]});
            }
        }

        return dp;
    }
}