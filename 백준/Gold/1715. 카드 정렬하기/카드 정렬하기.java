import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    PriorityQueue<Long> pq = new PriorityQueue<>();

    for (int i=0; i<N; i++) {
      pq.offer((long)Integer.parseInt(br.readLine()));
    }

    long answer = 0;
    while (pq.size() > 1) {
      long sum = pq.poll() + pq.poll();
      answer += sum;
      pq.offer(sum);
    }

    System.out.println(answer);
  }
}
