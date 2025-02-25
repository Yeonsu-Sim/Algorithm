import java.io.*;
import java.util.*;

public class Main {
    static long min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] line;
        long[][] input = new long[N][2];
        for (int n=0; n<N; n++) {
            line = br.readLine().split(" ");
            input[n][0] = Integer.parseInt(line[0]);
            input[n][1] = Integer.parseInt(line[1]);
        }

        comb(0, N, input, new int[N]);

        System.out.println(min);
    }

    static void comb(int depth, int N, long in[][], int out[]) {
        if (depth==N) {
            long sourSum = 1;
            long bitterSum = 0;
            for (int i=0; i<N; i++) {
                if(out[i] == 0) continue;
                sourSum *= in[i][0];
                bitterSum += in[i][1];
            }
            if (sourSum==1 && bitterSum==0) return;
            min = Math.min(min, Math.abs(sourSum - bitterSum));
            return;
        }

        out[depth] = 1;
        comb(depth+1, N, in, out);

        out[depth] = 0;
        comb(depth+1, N, in, out);
    }
}