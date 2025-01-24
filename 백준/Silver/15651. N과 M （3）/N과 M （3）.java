import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        dfs(0);
        System.out.println(sb.toString());
    }
    static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i=1; i<N+1; i++) {
            arr[depth] = i;
            dfs(depth+1);
        }
    }
}