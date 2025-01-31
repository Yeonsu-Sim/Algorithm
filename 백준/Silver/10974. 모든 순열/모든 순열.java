import java.io.*;

public class Main {
    static int N;
    static int[] output;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        output = new int[N];
        visited = new boolean[N];
        dfs(0);
    }
    static void dfs(int depth) {
        if (depth == N) {
            for (int i=0; i<N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i=0; i<N; i++) {
            if (visited[i]) {
                continue;
            }
            output[depth] = i+1;
            visited[i] = true;
            dfs(depth+1);
            visited[i] = false;
        }
    }
}