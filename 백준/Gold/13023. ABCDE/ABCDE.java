import java.io.*;
import java.util.*;

public class Main {
    
    static List<Integer>[] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st= new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        boolean isExist = false;
        for (int i=0; i<N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            if (dfs(1, i, visited)) {
                isExist = true;
                break;
            }
        }
        System.out.println(isExist ? 1 : 0);
    }

    public static boolean dfs(int depth, int cur, boolean[] visited) {
        if (depth == 5) return true;
        for (int next: graph[cur]) {
            if (visited[next]) continue;
            visited[next] = true;
            if (dfs(depth+1, next, visited)) return true;
            visited[next] = false;
        }
        return false;
    }
}