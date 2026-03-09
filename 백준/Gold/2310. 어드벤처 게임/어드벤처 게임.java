import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Room[] rooms;
    static int[] dp;  // 방을 지날 때 소지할 수 있는 coin의 최선
    static List<List<Integer>> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            rooms = new Room[N+1];
            dp = new int[N+1];
            Arrays.fill(dp, -1);
            edges = new ArrayList<>();
            for (int i=0; i<=N; i++) edges.add(new ArrayList<>());

            StringTokenizer st;
            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());

                char c = st.nextToken().charAt(0);
                int coin = Integer.parseInt(st.nextToken());
                rooms[i] = new Room(c, coin);

                int door = Integer.parseInt(st.nextToken());
                while (door != 0) {
                    edges.get(i).add(door);
                    door = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(dfs(1, 0) ? "Yes": "No").append('\n');
        }

        System.out.print(sb);
    }

    static int passing(int roomNo, int coin) {
        Room r = rooms[roomNo];
        if (r.c == 'E') return coin;
        else if (r.c == 'L') return coin < r.coin ? r.coin : coin;
        else return coin - r.coin;
    }

    static boolean dfs(int roomNo, int coin) {
        if (dp[roomNo] >= coin) return false;
        dp[roomNo] = coin;

        int nextCoin = passing(roomNo, coin);
        if (nextCoin < 0) return false;

        if (roomNo == N) return true;

        for (int next : edges.get(roomNo)) {
            if(dfs(next, nextCoin)) return true;
        }
        return false;
    }

    static class Room {
        char c;
        int coin;
        Room(char c, int coin) {
            this.c = c;
            this.coin = coin;
        }
    }
}