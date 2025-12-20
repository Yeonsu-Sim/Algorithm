import java.io.*;
import java.util.*;
/*
* ISSUE: A 고객의 목적지가 B고객의 출발지가 될 수 있음
* TIP: 되도록이면 거리 구하는 것을 마친 뒤에 (queue 안에서 해결) energy 계산하기
*/

public class Main {
  static class Pos implements Comparable<Pos> {
    int i,j, dist;
    public Pos(int i, int j) {
      this.i = i;
      this.j = j;
    }
    public Pos(int i, int j, int dist) {
      this.i = i;
      this.j = j;
      this.dist = dist;
    }
    public int compareTo(Pos n) {
      if (this.dist == n. dist) {
        if (this.i == n.i) {
          return this.j - n.j;
        }
        return this.i - n.i;
      }
      return this.dist - n.dist;
    }

    public String toString() {
      return "("+this.i + ", " + this.j+") "+this.dist;
    }
  }

  static int N, M, energy;
  static int[][] map;
  static Pos curPos, clientPos;
  static final int WALL = Integer.MAX_VALUE;
  static int[] dy = {-1,0,1,0};
  static int[] dx = {0,1,0,-1};
  static Pos[] dests;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    energy = Integer.parseInt(st.nextToken());

    map = new int[N][N];

    for (int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<N; j++) {
        int cur = Integer.parseInt(st.nextToken());
        map[i][j] = cur == 0 ? 0 : WALL;
      }
    }

    // 택시 위치 저장
    st = new StringTokenizer(br.readLine());
    int ci = Integer.parseInt(st.nextToken()) -1;
    int cj = Integer.parseInt(st.nextToken()) -1;
    curPos = new Pos(ci,cj);
    
    dests = new Pos[M+1];
    // 맵에 승객들 표시
    for (int i=1; i<=M; i++) {
      st = new StringTokenizer(br.readLine());
      
      int si = Integer.parseInt(st.nextToken()) - 1;
      int sj = Integer.parseInt(st.nextToken()) - 1;
      int di = Integer.parseInt(st.nextToken()) - 1;
      int dj = Integer.parseInt(st.nextToken()) - 1;
      
      map[si][sj] = i;
      dests[i] = new Pos(di, dj);
    }

    boolean failed = false;
    while (M-- > 0) {
      int dist = toClient();
      if (dist < 0 || energy <= dist) {
        failed = true;
        break;
      }
      energy -= dist;

      curPos = clientPos;
      int number = map[curPos.i][curPos.j];
      map[curPos.i][curPos.j] = 0;
      
      dist = toDest(number);
      if (dist < 0 || energy < dist) {
        failed =true;
        break;
      }

      energy += dist;
      curPos = dests[number];
    }
    
    // 최종적으로 남은 양 출력
    System.out.println(failed ? -1 : energy);

  }

  public static int toClient() {
    PriorityQueue<Pos> pq = new PriorityQueue<>();
    boolean[][] visited = new boolean[N][N];
    pq.offer(curPos);
    
    while (!pq.isEmpty()) {
      Pos cur = pq.poll();
      visited[cur.i][cur.j] = true;
      
      if (map[cur.i][cur.j] > 0) {
        clientPos = new Pos(cur.i, cur.j);
        return cur.dist;
      }
      
      for (int d=0; d<4; d++) {
        int ni = cur.i + dy[d];
        int nj = cur.j + dx[d];
        
        if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
        if (visited[ni][nj]) continue;
        if (map[ni][nj] == WALL) continue;

        pq.offer(new Pos(ni,nj, cur.dist+1));
        visited[ni][nj] = true;
      }
    }
    return -1;
  }

  public static int toDest(int number) {
    Queue<Pos> q = new ArrayDeque<>();
    boolean[][] visited = new boolean[N][N];
    q.offer(curPos);
    visited[curPos.i][curPos.j] = true;

    while (!q.isEmpty()) {
      Pos cur = q.poll();
      if (cur.i == dests[number].i && cur.j == dests[number].j) {
        return cur.dist;
      }

      for (int d=0; d<4; d++) {
        int ni = cur.i + dy[d];
        int nj = cur.j + dx[d];

        if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
        if (visited[ni][nj]) continue;
        if (map[ni][nj] == WALL) continue;

        q.offer(new Pos(ni,nj, cur.dist+1));
        visited[ni][nj] = true;
      }
    }

    return -1;
  }
}