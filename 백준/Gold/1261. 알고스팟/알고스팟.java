import java.util.*;
import java.io.*;

public class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[][] isWall = new boolean[N][M];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                isWall[i][j] = line.charAt(j) == '1';
            }
        }

        int[][] visited = new int[N][M];
        for (int[] v : visited) Arrays.fill(v, Integer.MAX_VALUE);

        PriorityQueue<Pos> pq= new PriorityQueue<>(
            (Pos a, Pos b) -> a.cnt - b.cnt
        );

        pq.offer(new Pos(0,0,0));
        visited[0][0] = 0;

        while (!pq.isEmpty()) {
            Pos cur = pq.poll();
            if (cur.i == N-1 && cur.j == M-1) break;

            for (int d=0; d<4; d++) {
                int ni = cur.i + dy[d];
                int nj = cur.j + dx[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;

                if (isWall[ni][nj]) {
                    if (visited[ni][nj] <= cur.cnt + 1) continue;
                    visited[ni][nj] = cur.cnt + 1;
                    pq.offer(new Pos(ni,nj,cur.cnt+1));
                } else {
                    if (visited[ni][nj] <= cur.cnt + 1) continue;
                    pq.offer(new Pos(ni,nj,cur.cnt));
                    visited[ni][nj] = cur.cnt;
                }
            }
        }

        System.out.println(visited[N-1][M-1]);
    }

    public static class Pos {
        int i; int j; int cnt;
        public Pos(int i, int j, int cnt) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}