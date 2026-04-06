import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        
        int[][] arr = new int[N][3];
        
        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int INF = 1_000_001;
        int[][] startR = new int[N][3];
        int[][] startG = new int[N][3];
        int[][] startB = new int[N][3];

        for (int[] r : startR) Arrays.fill(r, INF);
        for (int[] g : startG) Arrays.fill(g, INF);
        for (int[] b : startB) Arrays.fill(b, INF);

        startR[0][0] = arr[0][0];
        startG[0][1] = arr[0][1];
        startB[0][2] = arr[0][2];

        for (int i=1; i<N-1; i++) {  // i번째 집
            for (int j=0; j<3; j++) {  // i번째 집을 j 색으로 색칠
                for (int k=0; k<3; k++) {  // i-1번째 j색이 아닌 경우
                    if (j == k) continue;

                    startR[i][j] = Math.min(startR[i-1][k]+arr[i][j], startR[i][j]);
                    startG[i][j] = Math.min(startG[i-1][k]+arr[i][j], startG[i][j]);
                    startB[i][j] = Math.min(startB[i-1][k]+arr[i][j], startB[i][j]);
                }
            }
        }

        for (int j=0; j<3; j++) {
            for (int k=0; k<3; k++) {
                if (j == k) continue;

                if (j != 0) startR[N-1][j] = Math.min(startR[N-2][k]+arr[N-1][j], startR[N-1][j]);
                if (j != 1) startG[N-1][j] = Math.min(startG[N-2][k]+arr[N-1][j], startG[N-1][j]);
                if (j != 2) startB[N-1][j] = Math.min(startB[N-2][k]+arr[N-1][j], startB[N-1][j]);
            }
        }

        int min = INF;
        for (int i=0; i<3; i++) {
            min = Math.min(min, startR[N-1][i]);
            min = Math.min(min, startG[N-1][i]);
            min = Math.min(min, startB[N-1][i]);
        }
        System.out.println(min);
    }
}