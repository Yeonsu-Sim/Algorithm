import java.util.*;
import java.io.*;

public class Main {
    static List<List<int[]>> edges = new ArrayList<>();
    static Tree tree = new Tree();
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        for (int i=0; i<=N; i++) edges.add(new ArrayList<>());

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges.get(from).add(new int[]{to, i});
            edges.get(to).add(new int[]{from, i});
        }

        if (N <= 2) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int aCnt = 0;
        for (int i=1; i<=3; i++) {
            if (i == 3) {
                if (aCnt + tree.nodes.size() == N) break;
                System.out.println(-1);
                return;
            }

            tree = new Tree();
            makeTree();

            if (i == 2) {
                if (aCnt == tree.nodes.size()) {
                    System.out.println(-1);
                    return;
                }
            } else {
                aCnt = tree.nodes.size();
            }

            for (int n=0; n<tree.nodes.size()-1; n++) {
                sb.append(tree.nodes.get(n)+" ");
            }

            if (aCnt == N) {
                sb.append('\n');
                for (int e=0; e<tree.edges.size()-1; e++) sb.append(tree.edges.get(e)+" ");
                sb.append('\n');
                sb.append(tree.nodes.get(tree.nodes.size()-1));
                sb.append('\n');

                aCnt = N-1;
                break;
            } else {
                sb.append(tree.nodes.get(tree.nodes.size()-1));
                sb.append('\n');
            }

            for (int e: tree.edges) {
                sb.append(e+" ");
            }
            sb.append('\n');
        }

        System.out.println(aCnt + " "+(N-aCnt));
        System.out.print(sb);
    }

    public static void makeTree() {
        Stack<Integer> stack = new Stack<>();
        for (int i=1; i<visited.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            stack.add(i);
            tree.nodes.add(i);
            break;
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();

            for (int[] next: edges.get(cur)) {
                if (visited[next[0]]) continue;
                visited[next[0]] = true;
                tree.nodes.add(next[0]);
                tree.edges.add(next[1]);
                stack.add(next[0]);
            }
        }
        System.out.println();
    }

    static class Tree {
        List<Integer> nodes = new ArrayList<>();
        List<Integer> edges = new ArrayList<>();
    }
}