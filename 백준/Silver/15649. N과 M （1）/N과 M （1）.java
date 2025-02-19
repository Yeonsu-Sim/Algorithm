import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        backTracking(0, new boolean[N+1], new int[M]);
        System.out.println(sb);
    }

    static void backTracking(int m, boolean[] visited, int[] out) {
        if (m == M) {
            for (int i=0; i<M; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i=1; i<=N; i++) {
            if (visited[i]) continue;

            out[m] = i;
            visited[i] = true;
            backTracking(m+1, visited, out);
            visited[i] = false;
        }
    }
}
