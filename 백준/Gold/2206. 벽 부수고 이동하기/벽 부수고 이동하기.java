import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static class Info {
		int r, c, hop;

		public Info(int r, int c, int hop) {
			super();
			this.r = r;
			this.c = c;
			this.hop = hop;
		}

		@Override
		public String toString() {
			return "Info [r=" + r + ", c=" + c + ", hop=" + hop + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		String line; 
		for (int i=0; i<N; i++) {
			line = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		Queue<Info> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];
		q.offer(new Info(0,0,0));
		visited[0][0][0] = true;
		
		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			count++;
			
			for (int s=0; s<size; s++) {
				Info cur = q.poll();
				if (cur.r == N-1 && cur.c == M-1) {
					System.out.println(count);
					return;
				}
				for (int d=0; d<4; d++) {
					int ni = cur.r + dy[d];
					int nj = cur.c + dx[d];
					
					if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
					if (visited[ni][nj][cur.hop]) continue;
					
					// 그냥 지나갈 수 없는데, 부실 수 있는 경우
					if (map[ni][nj] == 1 && cur.hop < 1) {
						q.offer(new Info(ni,nj, cur.hop+1));
						visited[ni][nj][cur.hop+1] = true;
						continue;
					} else if (map[ni][nj] == 0) {
						q.offer(new Info(ni, nj, cur.hop));
						visited[ni][nj][cur.hop] = true;
					}
				}
			}
			
		}
		System.out.println(-1);
	}
}
