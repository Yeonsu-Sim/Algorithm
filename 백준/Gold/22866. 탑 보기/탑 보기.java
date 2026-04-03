import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int[][] dp1 = new int[N+1][2];  // 오른쪽 보기(인덱스, 개수)
        for (int i=N-1; i>0; i--) {
            int j = i+1;
            while (j<=N && j > 0 && arr[j] < arr[i]) j = dp1[j][0];
            
            if (j<=N && j > 0) {
                dp1[i][0] = arr[j] == arr[i] ? dp1[j][0] : j;
                dp1[i][1] = (arr[j] == arr[i] ? 0 : 1) + dp1[j][1];
            }
        }


        int[][] dp2 = new int[N+1][2];  // 왼쪽 보기
        for (int i=2; i<=N; i++) {
            int j = i-1;
            while (j>0 && arr[j] < arr[i]) j = dp2[j][0];
            
            if (j>0) {
                dp2[i][0] = arr[j] == arr[i] ? dp2[j][0] : j;
                dp2[i][1] = (arr[j] == arr[i] ? 0 : 1) + dp2[j][1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            int cnt = dp1[i][1] + dp2[i][1];
            sb.append(cnt+" ");
            
            int min = -1;
            if (dp1[i][1] != 0) min = dp1[i][0];
            if (dp2[i][1] != 0) {
                if (min == -1 || min - i >= i - dp2[i][0]) min = dp2[i][0];
            }
            if (min != -1) sb.append(min);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}