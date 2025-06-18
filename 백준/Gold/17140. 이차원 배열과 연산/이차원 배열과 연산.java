import java.io.*;
import java.util.*;

public class Main {
  static int R, C, K;
  static int rowCnt =3, colCnt =3, time =0;
  static int[][] arr = new int[100][100];
  static int[] map;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken()) -1;
    C = Integer.parseInt(st.nextToken()) -1;
    K = Integer.parseInt(st.nextToken());
    
    for (int i=0; i<3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<3; j++) {
        int num = Integer.parseInt(st.nextToken());
        arr[i][j] = num;
      }
    }

    while(time <= 100 && arr[R][C]!= K) {
      if (rowCnt >= colCnt) {
        updateRow();
      } else {
        updateCol();
      }
      time++;
    }
    System.out.println(time > 100 ? -1 : time);
  }
  
  static void updateRow() {
    int newColCnt = 0;
    for (int i=0; i<rowCnt; i++) {
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      map = new int[101];

      for (int j=0; j<colCnt; j++) {
        int num = arr[i][j];
        if (num == 0) continue;
        map[num]++;
        arr[i][j] = 0;
      }
      
      for (int num=1; num<101; num++) {
        int count = map[num];
        if (count == 0) continue;
        pq.offer(new Pair(num, count));
      }
      newColCnt = Math.max(newColCnt, pq.size()*2);

      int idx = 0;
      while (idx < 100 && !pq.isEmpty()) {
        Pair p = pq.poll();
        arr[i][idx++] = p.num;
        arr[i][idx++] = p.count;
      }
    }

    colCnt = newColCnt;
  }

  static void updateCol() {
    int newRowCnt = 0;
    for (int j=0; j<colCnt; j++) {
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      map = new int[101];
      
      for (int i=0; i<rowCnt; i++) {
        int num = arr[i][j];
        if (num == 0) continue;
        map[num]++;
        arr[i][j] = 0;
      }

      for (int num=1; num<101; num++) {
        int count = map[num];
        if (count == 0) continue;
        pq.offer(new Pair(num, count));
      }
      newRowCnt = Math.max(newRowCnt, pq.size()*2);

      int idx = 0;
      while (idx < 100 && !pq.isEmpty()) {
        Pair p = pq.poll();
        arr[idx++][j] = p.num;
        arr[idx++][j] = p.count;
      }
    }

    rowCnt = newRowCnt;
  }

  static class Pair implements Comparable<Pair> {
    int num, count;
    Pair(int num, int count) {
      this.num = num;
      this.count = count;
    }
    @Override
    public int compareTo(Pair o) {
      if (this.count == o.count) 
        return Integer.compare(this.num, o.num);
      return Integer.compare(this.count, o.count);
    }
  }
}
