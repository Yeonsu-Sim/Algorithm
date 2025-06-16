import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] durability;
    static boolean[] robots;
    static int start = 0; // 벨트 시작 위치 인덱스 (0부터 2N-1)
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        durability = new int[2 * N];
        robots = new boolean[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;
        int zeroCount = 0;

        while (zeroCount < K) {
            step++;

            // 1. 벨트 회전 (start 위치 이동)
            start = (start - 1 + 2 * N) % (2 * N);
            // 내리는 위치에 로봇 있으면 내리기
            int downPos = (start + N - 1) % (2 * N);
            if (robots[downPos]) {
                robots[downPos] = false;
            }

            // 2. 로봇 이동 (N-2부터 0까지)
            for (int i = N - 2; i >= 0; i--) {
                int cur = (start + i) % (2 * N);
                int next = (start + i + 1) % (2 * N);
                if (robots[cur] && !robots[next] && durability[next] > 0) {
                    robots[cur] = false;
                    robots[next] = true;
                    durability[next]--;
                    if (durability[next] == 0) zeroCount++;
                }
            }
            // 내리는 위치에 로봇 있으면 내리기
            if (robots[downPos]) {
                robots[downPos] = false;
            }

            // 3. 로봇 올리기 (start 위치)
            if (durability[start] > 0 && !robots[start]) {
                robots[start] = true;
                durability[start]--;
                if (durability[start] == 0) zeroCount++;
            }
        }

        System.out.println(step);
    }
}
