import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int[] arr = new int[N];
    for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i=N-1; i>0; i--) {
      int target = arr[i-1];

      if (arr[i] > target) {
        for (int j=i+1; j<N; j++) {
          if (arr[j] > target && arr[i] > arr[j]) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
          }
        }

        int tmp = arr[i];
        arr[i] = target;
        arr[i-1] = tmp;
        
        StringBuilder sb = new StringBuilder();
        for (int j=0; j<i; j++) {
          sb.append(arr[j]+" ");
        }

        for (int j=i; j<N; j++) pq.offer(arr[j]);

        while (!pq.isEmpty()) sb.append(pq.poll()+" ");
        System.out.print(sb);
        return;
      }
    }

    System.out.println(-1);
  }
}
