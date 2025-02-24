import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] NM = br.readLine().split(" ");
        int N = parseInt(NM[0]);
        int M = parseInt(NM[1]);
        int[] prefix = new int[N+1];

        String[] line = br.readLine().split(" ");
        for (int n=0; n<N; n++) {
            prefix[n+1] = prefix[n] + parseInt(line[n]);
        }

        for (int m=0; m<M; m++) {
            line = br.readLine().split(" ");
            int from = parseInt(line[0]);
            int to = parseInt(line[1]);

            sb.append(prefix[to] - prefix[from-1]).append('\n');
        }
        // 출력
        System.out.println(sb);
    }
}
