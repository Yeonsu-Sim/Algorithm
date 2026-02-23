import java.io.*;
import java.util.*;
public class Main{

    static int n, m;
    static ArrayList<ArrayList<Light>> graph = new ArrayList<>();
    static int[] visited;
    static class Light {
        int idx;
        int time;
        Light(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
    static class Node {
        int idx;
        long time;
        Node(int idx, long time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static long bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time, o2.time));
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.idx] == 1) {
                continue;
            }
            visited[cur.idx] = 1;
            if (cur.idx == n)  return cur.time;
            for (int i = 0;i < graph.get(cur.idx).size();i++) {
                Light next = graph.get(cur.idx).get(i);
                if (visited[next.idx] == 1) continue;
                if (cur.time % m <= next.time % m) {
                    pq.add(new Node(next.idx, cur.time + (next.time - (cur.time % m)) + 1));
                }
                else {
                    pq.add(new Node(next.idx, cur.time + (m - (cur.time % m) + next.time) + 1));
                }
            }
        }
        return -1;

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new int[n+1];
        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Light(b, i));
            graph.get(b).add(new Light(a, i));
        }

        System.out.println(bfs());
    }
}