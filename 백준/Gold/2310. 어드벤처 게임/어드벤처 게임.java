import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] type;
    static int[] value;
    static ArrayList<Integer>[] graph;

    static int enter(int room, int money) {
        if (type[room] == 'E') return money;
        if (type[room] == 'L') return Math.max(money, value[room]);
        return money - value[room]; // T
    }

    static boolean canReach() {
        int[] best = new int[N + 1];
        Arrays.fill(best, -1);

        Queue<Integer> q = new ArrayDeque<>();

        int startMoney = enter(1, 0);
        if (startMoney < 0) return false;

        best[1] = startMoney;
        q.offer(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curMoney = best[cur];

            if (cur == N) return true;

            for (int next : graph[cur]) {
                int nextMoney = enter(next, curMoney);
                if (nextMoney < 0) continue;

                if (best[next] < nextMoney) {
                    best[next] = nextMoney;
                    q.offer(next);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            type = new char[N + 1];
            value = new int[N + 1];
            graph = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                type[i] = st.nextToken().charAt(0);
                value[i] = Integer.parseInt(st.nextToken());

                while (true) {
                    int next = Integer.parseInt(st.nextToken());
                    if (next == 0) break;
                    graph[i].add(next);
                }
            }

            sb.append(canReach() ? "Yes" : "No").append('\n');
        }

        System.out.print(sb);
    }
}