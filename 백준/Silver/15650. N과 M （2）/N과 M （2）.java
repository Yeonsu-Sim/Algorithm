import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        backtrack(0, N, M, 1, new int[M]);
        System.out.print(sb);
    }

    static void backtrack(int cnt, int N, int M, int start, int[] out) {
        if (cnt == M) {
            for (int i=0; i<M; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i=start; i<N+1; i++) {
            out[cnt] = i;
            backtrack(cnt+1, N, M, i+1, out);
        }
    }
}