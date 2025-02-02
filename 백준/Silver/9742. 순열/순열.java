import java.io.*;
import java.util.*;

public class Main {
    static String N, result, base;
    static int M, cnt=1;
    static char[] in, out;
    static boolean[] visited;
    static boolean found;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] pair;

        while ((line = br.readLine()) != null) {
            pair = line.split(" ");
            N = pair[0];
            M = Integer.parseInt(pair[1]);
            base = N + " " + M + " = ";

            in = new char[N.length()];
            out = new char[N.length()];
            visited = new boolean[N.length()];

            for (int i = 0; i < N.length(); i++) {
                in[i] = N.charAt(i);
            }
            result = "No permutation";
            dfs(0);
            System.out.println(base + result);
            cnt = 1;
            found = false;
        }
    }

    static void dfs(int depth) {
        if (depth == N.length()) {
            if (cnt++ < M) return;
            String temp = "";
            for (int i = 0; i < N.length(); i++) {
                temp += out[i];
                result = temp;
            }
            found = true;
            return;
        }

        for (int i = 0; i < N.length(); i++) {
            if (found) return;
            if (visited[i]) continue;
            out[depth] = in[i];
            visited[i] = true;
            dfs(depth + 1);
            visited[i] = false;
        }
    }
}
    