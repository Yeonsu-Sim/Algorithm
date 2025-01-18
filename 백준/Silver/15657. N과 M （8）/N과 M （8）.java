import java.io.*;
import java.util.Arrays;


public class Main {
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		
		int nums = Integer.parseInt(line[0]);
		int count = Integer.parseInt(line[1]);
		String[] input = br.readLine().split(" ");
		visited = new boolean[nums];
		int[] result = new int[count];
		int[] numList = new int[nums];
		
		// 주어진 입력값을 배열에 저장
		for (int i=0; i<nums; i++) {
			numList[i] = Integer.parseInt(input[i]);
		}
		
//		// 입력값에 대한 오름차순 정렬
		for (int i=0; i<nums; i++) {
			for (int j=i+1; j<nums; j++) {
				if (numList[i] > numList[j]) {
					int temp = numList[i];
					numList[i] = numList[j];
					numList[j] = temp;
				}
			}
		}
		dfs(0,numList,result,0,count);	
		
		
	}
	// 중복 순열, 중복 조합, 조합, 순열
	//1 순열
	static void dfs(int depth, int[] numList,int[] out , int start, int count) {
		if(depth == count) {
			for(int i=0;i<out.length;i++) {
				System.out.print(out[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=start;i<numList.length;i++) {
			
			out[depth] = numList[i];
			dfs(depth+1,numList,out,i,count);
			
		}
	}
}
