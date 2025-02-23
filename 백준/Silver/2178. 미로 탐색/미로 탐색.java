import java.io.*;
import java.util.*;

public class Main {

    static class Pos {
        int x;
        int y;
        int count;

        Pos(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        int[][] input = new int[N][M];

        String line;
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }

        System.out.println(bfs(N, M, input));
    }

    static int bfs(int N, int M, int[][] input) {
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M]; // 방문 배열 추가

        int[] dx = {0, 0, -1, 1};  // 상하좌우
        int[] dy = {-1, 1, 0, 0};

        queue.offer(new Pos(0, 0, 1)); // 시작 지점
        visited[0][0] = true; // 시작 지점 방문 처리

        while (!queue.isEmpty()) {
            Pos pos = queue.poll();

            if (pos.y == N - 1 && pos.x == M - 1) { // 목적지 도달
                return pos.count;
            }

            for (int d = 0; d < 4; d++) {
                int x = pos.x + dx[d];
                int y = pos.y + dy[d];

                // 범위 체크
                if (x < 0 || x >= M || y < 0 || y >= N) continue;

                // 벽이거나 이미 방문한 곳은 건너뛰기
                if (input[y][x] == 0 || visited[y][x]) continue;

                visited[y][x] = true; // 방문 처리
                queue.offer(new Pos(x, y, pos.count + 1)); // 큐에 새로운 좌표와 count 추가
            }
        }
        return -1; // 경로가 없을 경우
    }
}
