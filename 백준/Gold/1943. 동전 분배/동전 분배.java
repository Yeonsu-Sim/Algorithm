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
            boolean[] dp = new boolean[half+1];
            dp[0] = true;

            for (int i = 0; i < N; i++) {
                int coin = arr[i][0];
                int cnt = arr[i][1];
                int[] used = new int[half + 1]; // 이 동전을 몇 개 써서 j에 도달했는지

                for (int j = coin; j <= half; j++) {
                    // 아직 도달 못했고, coin만큼 빼면 도달 가능하며, 개수 제한 안 넘으면
                    if (!dp[j] && dp[j - coin] && used[j - coin] < cnt) {
                        dp[j] = true;
                        used[j] = used[j - coin] + 1;
                    }
                }
            }
            sb.append((dp[half] ? '1' : '0') +"\n");
        }

        System.out.print(sb);
    }
}