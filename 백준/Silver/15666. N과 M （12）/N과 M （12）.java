import java.util.*;
import java.io.*;

public class Main {
    static int[] input;
    static int idx = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        input = new int[N];

        line = br.readLine().split(" ");
        for (String num: line) {
            int temp = Integer.parseInt(num);
            boolean duplicate = false;
            for (int i=0; i<=idx; i++) {
                if (input[i] == temp) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                input[idx++] = temp;
            }
        }
        Arrays.sort(input);

        dfs(0, new int[M], 0, M);
    }

    static void dfs (int depth, int[] out, int start, int size) {
        if (depth == size) {
            for (int i: out) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i=start; i<input.length; i++) {
            if (input[i] == 0) continue;
            out[depth] = input[i];
            dfs(depth+1, out, i, size);
        }
    }
}