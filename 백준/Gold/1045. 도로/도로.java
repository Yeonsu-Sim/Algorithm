import java.io.*;
import java.util.*;

public class Main {
  static int find(int a) {
    if (a == parents[a]) return a;
    return find(parents[a]);
  }

  static boolean isUnion(int a, int b) {
    if (find(a) == find(b)) return false;
    return true;
  }

  static void union(int a, int b) {
    parents[find(b)] = parents[find(a)];
  }

  static int N,M;
  static int[] parents;
  static int[] degs;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    parents = new int[N];
    for (int i=0; i<N; i++) {
      parents[i] = i;
    }

    int roadCount = 0;
    int[] degs = new int[N];

    Queue<int[]> tmp = new ArrayDeque<>();

    for (int i=0; i<N; i++) {
      String line = br.readLine();
      for (int j=i+1; j<N; j++) {
        if (line.charAt(j) == 'N') continue;
        if (isUnion(i,j)) {
          union(i,j);
          degs[i]++;
          degs[j]++;
          roadCount++;
        } else {
          tmp.add(new int[]{i,j});
        }
      }
    }

    // 모든 도시 방문 못하면 -1
    if (roadCount != N-1) {
      System.out.println(-1);
      return;
    }

    // 만약 남은 양보다 tmp가 적다면 -1
    if (M - roadCount > tmp.size()) {
      System.out.println(-1);
      return;
    }

    // 부족한 도로 절충 (우선순위에 따라 추가)
    // contains로 중복 제거 필요
    while (roadCount < M) {
      int[] cur = tmp.poll();
      degs[cur[0]]++;
      degs[cur[1]]++;
      roadCount++;
    }

    // 출력
    for (int d: degs) {
      System.out.print(d + " ");
    }
  }
  
}