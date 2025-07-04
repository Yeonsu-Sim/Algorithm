import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] NM = br.readLine().split(" ");
    int N = Integer.parseInt(NM[0]);
    int M = Integer.parseInt(NM[1]);

    int[] times = new int[N];

    for (int i=0; i<N; i++) {
      times[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(times);

    long start = 1;
    long end = (long)Math.pow(10,18);
    long answer = end;

    while (start <= end) {
      long mid =  (start + end)/2;

      long people = 0;
      for (int time: times) {
        people += mid / time;
        if (people >= M) break;  // 가지치기
      }

      if (people >= M) {
        answer = mid;
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    System.out.println(answer);
  }
}
