import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());  // 추 개수
		int[] weights = new int[N];
		st = new StringTokenizer(br.readLine());
		int total = 0;
		for (int i=0; i<N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			total += weights[i];
		}

		int M = Integer.parseInt(br.readLine());  // 구슬 개수
		int[] marbles = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) marbles[i] = Integer.parseInt(st.nextToken());

		boolean[] dp = new boolean[total+1];
		dp[0] = true;

		for (int weight: weights) {
			for (int i=total; i>=weight; i--) {
				if (dp[i-weight]) dp[i] = true;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int marble : marbles) {
			boolean answer = false;

			for (int i=0; i<=total-marble; i++) {
				if (dp[i] && dp[i+marble] && dp[2*i + marble]) {
					answer = true;
					break;
				}
			}
			
			if (answer) sb.append("Y ");
			else sb.append("N ");
		}
		System.out.println(sb);
	}
}