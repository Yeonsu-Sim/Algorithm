import java.io.*;
import java.util.*;

public class Main {
    
    static int N, max;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new int[N], new boolean[N]);

        System.out.println(max);

    }

    public static void dfs(int depth, int[] tmp, boolean[] visited) {
        if (depth == N) {
            max = Math.max(max, calc(tmp));
            return;
        }

        for (int i=0; i<N; i++) {
            if (visited[i]) continue;
            tmp[depth] = i;
            visited[i] = true;
            dfs(depth+1, tmp, visited);
            visited[i] = false;
        }
    }

    public static int calc(int[] tmp) {
        int result = 0;
        for (int i=0; i<N-1; i++) {
            result += Math.abs(arr[tmp[i]] - arr[tmp[i+1]]);
        }
        return result;
    }
}