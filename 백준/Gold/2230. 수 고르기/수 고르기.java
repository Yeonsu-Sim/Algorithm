import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 0;
        int right = left;
        int answer = Integer.MAX_VALUE;

        Arrays.sort(arr);

        while (left <= right && left < N-1) {
            int gap = arr[right] - arr[left];
            
            if (gap == M) {
                answer = M;
                break;
            } else if (gap < M) {
                if (right == N-1) break;
                right++;
            } else {
                answer = Math.min(gap, answer);
                left++;
            }
        }
        
        System.out.println(answer);
    }
}