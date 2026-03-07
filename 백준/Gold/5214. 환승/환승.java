import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] tubes = new int[M][K];
        List<List<Integer>> stations = new ArrayList<>();
        for (int i=0; i<N+1; i++) stations.add(new ArrayList<>());

        for (int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int[] tmp = new int[K];
            for (int k=0; k<K; k++) {
                int station = Integer.parseInt(st.nextToken());
                tmp[k] = station;
                stations.get(station).add(m);
            }
            tubes[m] = tmp;
        }

        int[] visited = new int[N+1];
        Arrays.fill(visited, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == N) break;
            int cnt = visited[cur];

            for (int tube : stations.get(cur)) {
                for (int next : tubes[tube]) {
                    if (visited[next]>=0) continue;
                    visited[next] = cnt+1;
                    q.offer(next);
                }
            }
        }
        
        System.out.println(visited[N]);
    }
}