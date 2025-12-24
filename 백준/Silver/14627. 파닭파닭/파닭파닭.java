import java.io.*;
import java.util.*;

public class Main {
  static int N,C;
  static int[] arr;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    arr = new int[N];
    int start = 1;
    int end = 0;
    long sum = 0;

    for (int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
      end = Math.max(end, arr[i]);
      sum += arr[i];
    }


    while (start <= end) {
      int mid = (start+end)/2;
      if (calc(mid) >= C) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    System.out.println(sum - (long) end*C);

  }

  public static long calc(int length) {
    long count = 0;
    for (int i=0; i<N; i++) {
      count += arr[i]/length;
    }
    return count;
  }
}