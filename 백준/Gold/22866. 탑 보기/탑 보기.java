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


        int[] dp1 = new int[N+1];  // 오른쪽 보기
        int[] min1 = new int[N+1];
        for (int i=N-1; i>0; i--) {
            int j = i+1;
            while (j!=0 && arr[j] <= arr[i]) j = min1[j];
            
            if (j!=0) {
                dp1[i] = 1 + dp1[j];
                min1[i] = j;
            }
        }


        int[] dp2 = new int[N+1];  // 왼쪽 보기
        int[] min2 = new int[N+1];
        for (int i=2; i<=N; i++) {
            int j = i-1;
            while (j!=0 && arr[j] <= arr[i]) j = min2[j];
            
            if (j!=0) {
                dp2[i] = 1 + dp2[j];
                min2[i] = j;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            int cnt = dp1[i] + dp2[i];
            sb.append(cnt+" ");
            
            int min = -1;
            if (dp1[i] != 0) min = min1[i];
            if (dp2[i] != 0) {
                if (min == -1 || min - i >= i - min2[i]) min = min2[i];
            }
            if (min != -1) sb.append(min);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}