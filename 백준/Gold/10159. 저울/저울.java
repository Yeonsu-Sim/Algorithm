import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<List<Integer>> in = new ArrayList<>();
        List<List<Integer>> out = new ArrayList<>();

        for (int i=0; i<N+1; i++) {
            in.add(new ArrayList<>());
            out.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            in.get(from).add(to);
            out.get(to).add(from);
        }

        StringBuilder sb = new StringBuilder();
        Queue<int[]> q = new ArrayDeque<>();
        for (int i=1; i<N+1; i++) {
            int unknownCnt = N-1;
            boolean[][] visited = new boolean[N+1][2];

            q.offer(new int[]{i, 0});
            q.offer(new int[]{i, 1});
            visited[i][0] = true;
            visited[i][1] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int num = cur[0];
                int dir = cur[1];

                List<Integer> graph = dir > 0 ? in.get(num) : out.get(num);

                for (int next : graph) {
                    if (!visited[next][dir]) {
                        q.offer(new int[]{next, dir});
                        unknownCnt--;
                        visited[next][dir] = true;
                    }
                }
            }

            sb.append(unknownCnt).append('\n');
        }

        System.out.println(sb);
    }
}
