import java.io.*;
import java.util.Arrays;

public class Main {
    static int L, C;
    static char[] arr, out;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        L = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);

        arr = new char[C];
        out = new char[L];

        String chars = br.readLine();
        for (int i = 0; i < C; i++) {
            arr[i] = chars.charAt(i * 2);
        }
        Arrays.sort(arr);

        dfs(0, 0);
        System.out.println(sb.toString());
    }

    static void dfs(int depth, int start) {
        if (depth == L) {
            int vCnt = 0, cCnt = 0;
            for (char c : out) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    vCnt++;
                } else {
                    cCnt++;
                }
            }
            if (vCnt >= 1 && cCnt >= 2) {
                for (char c : out) {
                    sb.append(c);
                }
                sb.append('\n');
            }
            return;
        }

        for (int i = start; i < C; i++) {
            out[depth] = arr[i];
            dfs(depth + 1, i + 1);
        }
    }
}