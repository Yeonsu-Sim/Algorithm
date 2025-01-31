import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int arr[], result[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		result = new int[M];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		DFS(1, 0);
		System.out.println(sb);
	}
	
	public static void DFS(int start, int depth) {
		int num = 0;
		
		if(depth == M) {
			for(int k : result)
				sb.append(k).append(' ');
			
			sb.append('\n');
			return;
		}
		
		for(int i=start; i<=N; i++) {
			if(num == arr[i]) continue;
			
			result[depth] = arr[i];
			DFS(i+1, depth+1);
			num = arr[i];	
		}
	}
}