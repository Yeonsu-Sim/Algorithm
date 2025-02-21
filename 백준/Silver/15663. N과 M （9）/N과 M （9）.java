import java.io.*;
import java.util.Arrays;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        int[] input = new int[N];

        String[] line = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            input[i] = Integer.parseInt(line[i]);
        }

        Arrays.sort(input);

        backTrack(0, N, M, input, new int[M], new boolean[N]);
        System.out.println(sb);
    }

    static void backTrack(int cnt, int N, int M, int[] input, int out[], boolean[] visited) {
        if (cnt == M) {
            for (int i=0; i<M; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int prev = -1;
        for (int i=0; i<N; i++) {
            if (visited[i]) continue;
            if (prev == input[i]) continue;
            out[cnt] = input[i];
            prev = input[i];
            visited[i] = true;
            backTrack(cnt+1, N, M, input, out, visited);
            visited[i] = false;
        }
    }
}
