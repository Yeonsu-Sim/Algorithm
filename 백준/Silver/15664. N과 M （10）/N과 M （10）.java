import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] in, out;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        in = new int[N];
        out = new int[M];

        line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(line[i]);
        }
        Arrays.sort(in);
        dfs(0, 0);
        System.out.println(sb);
    }

    static void dfs(int depth, int start) {
        int num = 0;

        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < N; i++) {
            if (num == in[i]) continue;
            out[depth] = in[i];
            dfs(depth + 1, i + 1);
            num = in[i];  // 중복 순열 제거
        }
    }
}