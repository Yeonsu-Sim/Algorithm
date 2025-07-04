import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> blackList = new ArrayList<>();
        List<Integer> whiteList = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            blackList.add(Integer.parseInt(parts[0]));
            whiteList.add(Integer.parseInt(parts[1]));
        }

        int N = blackList.size();
        int[] black = new int[N];
        int[] white = new int[N];

        for (int i = 0; i < N; i++) {
            black[i] = blackList.get(i);
            white[i] = whiteList.get(i);
        }

        // DP 테이블: dp[i][b][w]
        int[][][] dp = new int[N + 1][16][16]; // 최대 15명까지니까 0~15 인덱스

        for (int i = 0; i < N; i++) {
            for (int b = 0; b <= 15; b++) {
                for (int w = 0; w <= 15; w++) {
                    // 1. 선택 안 함
                    dp[i + 1][b][w] = Math.max(dp[i + 1][b][w], dp[i][b][w]);

                    // 2. 흑팀 선택
                    if (b < 15) {
                        dp[i + 1][b + 1][w] = Math.max(dp[i + 1][b + 1][w], dp[i][b][w] + black[i]);
                    }

                    // 3. 백팀 선택
                    if (w < 15) {
                        dp[i + 1][b][w + 1] = Math.max(dp[i + 1][b][w + 1], dp[i][b][w] + white[i]);
                    }
                }
            }
        }

        // 정답: N명까지 고려했을 때 흑15명, 백15명의 최대 점수
        System.out.println(dp[N][15][15]);
    }
}