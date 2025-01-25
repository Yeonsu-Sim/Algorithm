import java.io.*;
import java.util.Arrays;

public class Main {
    static int N, M, answer = 0;
    static int[] arr, tmp, out;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int n = N;
        tmp = new int[6];
        while (n / 10 > 0) {
            tmp[M++] = n % 10;
            n /= 10;
        }
        tmp[M++] = n;
        arr = new int[M];
        visited = new boolean[M];
        out = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = tmp[i];
        }

        Arrays.sort(arr);
        dfs(0);
        System.out.println(answer);

    }
    static void dfs(int depth) {
        if (answer != 0) {
            return;
        }
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(out[i]);
            }
            int n = Integer.parseInt((sb.toString()));
            if (N < n) {
                answer = n;
            }
            sb.setLength(0);
            return;
        }

        for (int i=0; i<M; i++) {
            if (visited[i]) {
                continue;
            }
            out[depth] = arr[i];
            visited[i] = true;
            dfs(depth+1);
            visited[i] = false;
        }
    }
}