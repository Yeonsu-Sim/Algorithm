import java.io.*;
import java.util.*;


public class Main {
    static int gap , size, min , max, result;
    static int[] in;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        // 조건 저장
        size = Integer.parseInt(line[0]);
        min = Integer.parseInt(line[1]);
        max = Integer.parseInt(line[2]);
        gap = Integer.parseInt(line[3]);


        String[] input = br.readLine().split(" ");

        // 주어진 숫자 저장
        in = new int[input.length];
        for (int i=0; i<input.length; i++) {
            in[i] = Integer.parseInt(input[i]);
        }

        result = 0;

        for (int i=2; i<=size; i++) {
            // dfs 시작
            dfs(0, new int[i], i, 0);
        }

        System.out.println(result);

    }

    static void dfs(int depth, int[] out, int pick, int start) {

        if (depth == pick ){
            int sum=0;
            int easy = out[0];
            int hard = out[0];

            for (int n : out) {
                sum += n;
                easy = Math.min(easy,n);
                hard = Math.max(hard, n);
            }

            // min과 max 사이인지, gap 이상인지 확인하기
            if (sum >= min && sum <= max && (hard-easy >= gap)) {
                // result 갱신하기
                result++;
            }
            return;
        }

        // size 만큼 뽑기
        for (int i=start; i<in.length; i++) {
            out[depth] = in[i];
            dfs(depth+1, out, pick, i+1);
        }
    }
}