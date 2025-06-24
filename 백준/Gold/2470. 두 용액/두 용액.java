import java.io.*;
import java.util.*;

public class Main {
  static class Pair implements Comparable<Pair>{
    int n1,n2,sum;
    Pair(int n1, int n2, int sum) {
      this.n1 = n1;
      this.n2 = n2;
      this.sum = sum;
    }
    @Override
    public int compareTo(Pair o) {
      return Integer.compare(this.sum, o.sum);
    }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    TreeSet<Integer> negs = new TreeSet<>(Collections.reverseOrder());
    TreeSet<Integer> poses = new TreeSet<>();
    PriorityQueue<Pair> pairs = new PriorityQueue<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i=0; i<N; i++) {
      int num = Integer.parseInt(st.nextToken());
      if (num > 0) {
        poses.add(num);
      } else {
        negs.add(num);
      }
    }

    // 각 음수에 대해 최적의 짝궁찾기
    for (int cur: negs) {
      Integer neg = negs.first();
      if (cur == neg) {
        neg = negs.lower(cur);
      }
      int negSum = Integer.MAX_VALUE;
      if (neg != null) {
        negSum = Math.abs(cur + neg);
      }
      
      int abs = cur*-1;
      Integer ceil = poses.ceiling(abs);
      Integer ceilSum = null;
      if (ceil != null) {
        ceilSum = Math.abs(cur + ceil);
      }
      Integer floor = poses.floor(abs);
      Integer floorSum = null;
      if (floor != null) {
        floorSum = Math.abs(cur + floor);
      }
      
      Integer pos = null;
      Integer posSum = null;
      if (ceil == null && floor == null) {
        posSum = Integer.MAX_VALUE;
      } else if (ceil == null) {
        pos = floor;
        posSum = floorSum;
      } else if (floor == null) {
        pos = ceil;
        posSum = ceilSum;
      } else {
        if (ceilSum < floorSum) {
          posSum = ceilSum;
          pos = ceil;
        } else {
          posSum = floorSum;
          pos = floor;
        }
      }
      
      if (neg != null && negSum < posSum) {
          pairs.offer(new Pair(cur, neg, negSum));
      } else if (pos != null) {
          pairs.offer(new Pair(cur, pos, posSum));
      }
    }
    
    // 각 양수에 대해 최적의 짝궁찾기
    for (int cur: poses) {
      Integer pos = poses.first();
      if (cur == pos) {
        pos = poses.higher(cur);
      }
      int posSum = Integer.MAX_VALUE;
      if (pos != null) {
        posSum = Math.abs(cur + pos);
      }

      int abs = cur*-1;
      Integer ceil = negs.ceiling(abs);
      Integer ceilSum = null;
      if (ceil != null) {
        ceilSum = Math.abs(cur + ceil);
      }
      Integer floor = negs.floor(abs);
      Integer floorSum = null;
      if (floor != null) {
        floorSum = Math.abs(cur + floor);
      }
      
      Integer neg = null;
      Integer negSum = null;
      if (ceil == null && floor == null) {
        negSum = Integer.MAX_VALUE;
      } else if (ceil == null) {
        neg = floor;
        negSum = floorSum;
      } else if (floor == null) {
        neg = ceil;
        negSum = ceilSum;
      } else {
        if (ceilSum < floorSum) {
          negSum = ceilSum;
          neg = ceil;
        } else {
          negSum = floorSum;
          neg = floor;
        }
      }

      if (pos != null && posSum < negSum) {
          pairs.offer(new Pair(cur, pos, posSum));
      } else if (neg != null) {
          pairs.offer(new Pair(cur, neg, negSum));
      }
    }


    Pair answer = pairs.poll();
    int n1 = answer.n1; int n2 = answer.n2;
    System.out.print(Math.min(n1,n2)+" "+Math.max(n1,n2));
  }
}
