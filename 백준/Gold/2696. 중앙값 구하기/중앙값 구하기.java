import java.io.*;
import java.util.*;

public class Main {
  static int M;
  static Integer mid;
  static PriorityQueue<Integer> prev = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
  static PriorityQueue<Integer> next = new PriorityQueue<>((a,b) -> Integer.compare(a,b));

  static void insert(int num) {
    if (mid == null) {
      mid = num;
    } else {
      if (mid < num) {
        next.offer(num);
      } else {
        prev.offer(num);
      }
    }
  }
  
  static void getBalance() {
    if (prev.size() > next.size() + 1) {
      int newMid = prev.poll();
      next.offer(mid);
      mid = newMid;
    } else if (prev.size() + 1 < next.size()) {
      int newMid = next.poll();
      prev.offer(mid);
      mid = newMid;
    }
  }
  
  static void init() {
    mid = null;
    prev.clear();
    next.clear();
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int t=0; t<T; t++) {
      M = Integer.parseInt(br.readLine());
      Queue<Integer> buffer = new ArrayDeque<>();
      init();

      for (int i=0; i<M/10; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<10; j++) {
          int num = Integer.parseInt(st.nextToken());
          insert(num);
          if (j%2 == 0) {
            getBalance();
            buffer.offer(mid);
          }
        }
      }
      
      if (M%10 > 0) {
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M%10; i++) {
          int num = Integer.parseInt(st.nextToken());
          insert(num);
          if (i%2 == 0) {
            getBalance();
            buffer.offer(mid);
          }
        }
      }

      int count = 0;
      sb.append(buffer.size()).append('\n');
      while (!buffer.isEmpty()) {
        sb.append(buffer.poll());
        if (++count % 10 == 0 && !buffer.isEmpty()) {
          sb.append('\n');
        } else {
          sb.append(' ');
        }
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
}
