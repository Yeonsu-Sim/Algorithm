import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] acc;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        acc = new int[N+1][N+1];

        for (int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<N+1; j++) {
                int v = Integer.parseInt(st.nextToken());
                acc[i][j] = v + getUp(i,j) + getLeft(i,j) - getLeftUp(i,j);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = acc[x2][y2] - getLeft(x2,y1) - getUp(x1,y2) + getLeftUp(x1, y1);

            sb.append(sum).append('\n');
        }

        System.out.println(sb);
    }

    static int getUp(int i, int j) { return acc[i-1][j]; }
    static int getLeft(int i, int j) { return acc[i][j-1]; }
    static int getLeftUp(int i, int j) { return acc[i-1][j-1]; }

}