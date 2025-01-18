import java.io.*;
import java.util.*;


public class Main {
    static int N, L , R, X, result;
    static int[] in;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        // 조건 저장
        N = Integer.parseInt(line[0]);
        L = Integer.parseInt(line[1]);
        R = Integer.parseInt(line[2]);
        X = Integer.parseInt(line[3]);


        String[] input = br.readLine().split(" ");

        // 주어진 숫자 저장
        in = new int[input.length];
        for (int i=0; i<input.length; i++) {
            in[i] = Integer.parseInt(input[i]);
        }

        result = 0;

        for (int i=0; i<(1<<N); i++) {
            int cnt = 0;
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int j=0; j<N; j++) {
                if ((i & (1<<j)) > 0) {
                    cnt++;
                    sum += in[j];
                    min = Math.min(min, in[j]);
                    max = Math.max(max, in[j]);
                }
            }
            if (cnt<2 || sum < L || sum > R || max - min < X) continue;
            result++;
        }

        System.out.println(result);

    }
}
