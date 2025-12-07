import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 *
 * ISSUE: Integer.MAX_VALUE
 *
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = parseInt(NK[0]);
		int K = parseInt(NK[1]);
		int[] lans = new int[N];
		
		for (int i=0; i<N; i++) {
			int num = parseInt(br.readLine());
			lans[i] = num;
		}
		
		System.out.println(split(K, (long)Integer.MAX_VALUE, lans));
	}
	
	/* 2분 탐색을 통해 적정 길이 추출 */
	static long split(int K, long end, int[] lans) {
		long length = 0;
		
		long start = 1;  // 최소는 1로 나누는 것!
		while (start <= end) {
			length = (start + end) / 2;
			
			int count = count(length, lans);
			
			if (count < K) {
				end = length-1;
			} else {
				start = length+1;
			}
		}
				
		return end;
	}
	
	/* 자른 랜선 개수 구하기 */
	static int count(long length, int[] lans) {
		int count = 0; 
		for (int i=0; i<lans.length; i++) {
			count += lans[i] / length;  // length보다 작으면 버림
		}
		return count;
	}
}
