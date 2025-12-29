import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static class State {
        int screen;
        int clipboard;
        int time;

        State(int screen, int clipboard, int time) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[1001][1001];
        Queue<State> queue = new ArrayDeque<>();

        // 초기 상태
        queue.offer(new State(1, 0, 0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            int screen = cur.screen;
            int clipboard = cur.clipboard;
            int time = cur.time;

            // 목표 도달
            if (screen == S) {
                System.out.println(time);
                return;
            }

            // 1. 복사 (화면 → 클립보드)
            if (!visited[screen][screen]) {
                visited[screen][screen] = true;
                queue.offer(new State(screen, screen, time + 1));
            }

            // 2. 붙여넣기 (클립보드 → 화면)
            if (clipboard > 0 && screen + clipboard <= 1000) {
                if (!visited[screen + clipboard][clipboard]) {
                    visited[screen + clipboard][clipboard] = true;
                    queue.offer(new State(screen + clipboard, clipboard, time + 1));
                }
            }

            // 3. 삭제 (화면 - 1)
            if (screen > 0) {
                if (!visited[screen - 1][clipboard]) {
                    visited[screen - 1][clipboard] = true;
                    queue.offer(new State(screen - 1, clipboard, time + 1));
                }
            }
        }
    }
}
