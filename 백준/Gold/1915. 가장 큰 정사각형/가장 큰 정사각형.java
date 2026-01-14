import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = {-1, -1, 0};
    static int[] dx = {-1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i=1; i<N; i++) {
            for (int j=1; j<M; j++) {

                int a = arr[i+dy[0]][j+dx[0]];
                int b = arr[i+dy[1]][j+dx[1]];
                int c = arr[i+dy[2]][j+dx[2]];
                int d = arr[i][j];

                if (a == 0 || b == 0 || c == 0 || d == 0) continue;

                if (a == b && b == c) {
                    arr[i][j] = a + 1;
                } else {
                    int min = Math.min(a,b);
                    min = Math.min(min, c);

                    arr[i][j] = min+1;
                }
            }
        }

        int max = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }

        // for (int[] a: arr) System.out.println(Arrays.toString(a));

        System.out.println(max*max);
    }
}