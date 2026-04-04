import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            String line = br.readLine();
            if (line == null) break;

            Integer N = Integer.parseInt(line);
            int[][] arr = new int[N][2];

            int sum = 0;
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                arr[i][0] = coin;
                arr[i][1] = cnt;
                sum += (coin*cnt);
            }

            if (sum % 2 == 1) {
                sb.append("0\n");
                continue;
            }
            
            int half = sum/2;
            int[] dp = new int[half+1];
            dp[0] = 1;

            for (int i=0; i<N; i++) {
                int[] tmp = new int[half+1];  // 동전 쓴 개수 저장
                for (int j=0; j<half+1; j++) {
                    if (dp[j] == 0) continue;
                    
                    int next = j+arr[i][0];
                    if (next > half) break;
                    if (dp[next] == 1) continue;

                    if (tmp[j] == arr[i][1]) continue;
                    tmp[next] = tmp[j]+1;
                    dp[next] = 1;
                }
            }
            sb.append(dp[half]+"\n");
        }

        System.out.print(sb);
    }
}