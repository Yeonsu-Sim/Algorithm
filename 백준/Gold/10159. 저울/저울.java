import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		boolean[][] arr = new boolean[n + 1][n + 1];

		int m = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[a][b] = true;
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (!arr[i][k])
					continue;
				for (int j = 1; j <= n; j++) {
					if (arr[k][j])
						arr[i][j] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=n;i++) {
			int unknown = 0;
			for(int j=1;j<=n;j++) {
				if(i == j )continue;
				boolean ij = arr[i][j];
				boolean ji = arr[j][i];
				if((ij && !ji) || (!ij && ji)){
					continue;
				}
				if (!ij && !ji) {
					unknown++;
		    }
			}
			sb.append(unknown).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}