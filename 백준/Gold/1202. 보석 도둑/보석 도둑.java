import java.io.*;
import java.util.*;

public class Main {
  static class Jam {
    int weight, value;
    Jam(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
    @Override
    public String toString() {
      return "["+weight+","+value+"]";
    }
  }

  static int N,K;
  static Jam[] jams;
  static Jam[] bags;
  public static void main(String[] args) throws Exception {
    init();
    solve();
    System.out.println(maxSum());
  }

  static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    jams = new Jam[N];
    bags = new Jam[K];

    for (int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int V = Integer.parseInt(st.nextToken());
      jams[i] = new Jam(M,V);
    }
    
    for (int i=0; i<K; i++) {
      int M = Integer.parseInt(br.readLine());
      bags[i] = new Jam(M, 0);
    }
  }

  static void solve() {

    // 보석 무게의 오름차순 정렬
    Arrays.sort(jams, new Comparator<Jam>() {
      @Override
      public int compare(Jam o1, Jam o2) {
        return Integer.compare(o1.weight, o2.weight);
      }
    });

    // 가방 무게의 오름차순 정렬
    Arrays.sort(bags, new Comparator<Jam>() {
      @Override
      public int compare(Jam o1, Jam o2) {
        return Integer.compare(o1.weight, o2.weight);
      }
    });
    
    PriorityQueue<Jam> pq = new PriorityQueue<>(new Comparator<Jam>() {
      @Override
      public int compare(Jam o1, Jam o2) {
        return Integer.compare(o2.value, o1.value);
      }
    });

    // 가방에 들어갈 수 있는 가장 비싼 보석 고르기
    int jamIdx = 0;
    for (int i=0; i<K; i++) {
      Jam bag = bags[i];
      while (jamIdx < N && bag.weight >= jams[jamIdx].weight) {
        pq.offer(jams[jamIdx++]);
      }

      if (!pq.isEmpty()) {
        bag.value = pq.poll().value;
      }
    }
  }

  static long maxSum() {
    long sum = 0;
    for (int i=0; i<K; i++) {
      sum += bags[i].value;
    }
    return sum;
  }
}
