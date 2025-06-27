import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] adj;
    static boolean[] visited, inCycle;
    static int[] dist;
    static Deque<Integer> stack = new ArrayDeque<>();
    static boolean cycleFound = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        visited = new boolean[N+1];
        inCycle = new boolean[N+1];

        // 사이클 찾기
        dfs(1, -1);

        // 다중 출발 BFS로 거리 계산
        dist = new int[N+1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inCycle[i]) {
                dist[i] = 0;
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(' ');
        }
        System.out.println(sb);
    }

    // DFS로 사이클 정점들 찾기
    static void dfs(int u, int parent) {
        if (cycleFound) return;
        visited[u] = true;
        stack.push(u);

        for (int v : adj[u]) {
            if (v == parent) continue;
            if (!visited[v]) {
                dfs(v, u);
                if (cycleFound) return;
            } else {
                // 이미 방문된 정점을 만났고, 사이클이 아직 처리되지 않았다면
                cycleFound = true;
                // 스택에서 v가 나올 때까지 꺼내서 사이클 표시
                Iterator<Integer> it = stack.iterator();
                while (it.hasNext()) {
                    int node = it.next();
                    inCycle[node] = true;
                    if (node == v) break;
                }
                return;
            }
        }

        // 되돌아가면서 스택에서 제거
        if (!cycleFound) stack.pop();
    }
}
