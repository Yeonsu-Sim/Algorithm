import java.io.*;
import java.util.*;

class Main {
    static int N, M, R, answer;
    static int[] visited;
    static List<List<Integer>> list = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        R = Integer.parseInt(line[2]);

        visited = new int[N];
        for (int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            list.get(Integer.parseInt(line[0])).add(Integer.parseInt(line[1]));
            list.get(Integer.parseInt(line[1])).add(Integer.parseInt(line[0]));
        }
        for (List<Integer> l : list) {
            Collections.sort(l, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o2 > o1) {
                        return 1;
                    } else if (o2 < o1) {
                        return -1;
                    }
                    return 0;
                }
            });
        }

        bfs(R);
        for (int order : visited) {
            System.out.println(order);
        }
    }

    static void bfs(int node) {
        int order = 1;
        visited[node-1] = order++;

        for (int v : list.get(R)) {
            queue.offer(v);
            visited[v-1] = order++;
        }

        while (!queue.isEmpty()) {
            int visit = queue.poll();

            for (int i = 0; i < list.get(visit).size(); i++) {
                int next = list.get(visit).get(i);
                if (visited[next - 1] == 0) {
                    queue.offer(next);
                    visited[next - 1] = order++;
                }
            }
        }
    }
}