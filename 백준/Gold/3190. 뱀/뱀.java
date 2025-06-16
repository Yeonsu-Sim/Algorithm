import java.io.*;
import java.util.*;

public class Main {
  static class Pos {
    int r, c;

    Pos(int r, int c) {
      this.r = r;
      this.c = c;
    }
    @Override
    public String toString() {
      return r + "," + c;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pos)) return false;
      Pos pos = (Pos) o;
      return r == pos.r && c == pos.c;
    }
    @Override
    public int hashCode() {
      return Objects.hash(r, c);
    }
  }

  static class Oper implements Comparable<Oper> {
    int time;
    char dir;
    Oper (int time, char dir) {
      this.time = time;
      this.dir = dir;
    }
    @Override
    public int compareTo(Oper o) {
      return Integer.compare(this.time, o.time);
    }
    @Override
    public String toString() {
      return time + " " + dir;
    }
  }

  public static boolean isEnd() {
    Pos head = snake.get(0);
    if (!isIn(head) || isMe(head)) return true;
    return false;
  }

  public static boolean isMe(Pos pos) {
    for (int i=1; i<snake.size(); i++) {
      if (snake.get(i).equals(pos)) return true;
    }
    return false;
  }

  public static boolean isIn(Pos pos) {
    int r = pos.r; int c = pos.c;
    return r >= 0 && r < N && c >= 0 && c < N;
  }
  
  static List<Pos> snake = new ArrayList<>();
  static HashSet<Pos> apples = new HashSet<>();
  static PriorityQueue<Oper> opers = new PriorityQueue<>();
  static int N, K, L;
  static int time = 0, dir = 0;
  static int[] dx = {1,0,-1,0};
  static int[] dy = {0,1,0,-1};
  public static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());

    K = Integer.parseInt(br.readLine());
    for (int i=0; i<K; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      apples.add(new Pos(r, c));
    }
    L = Integer.parseInt(br.readLine());
    for (int i=0; i<L; i++) {
      st = new StringTokenizer(br.readLine());
      int X = Integer.parseInt(st.nextToken());
      char C = st.nextToken().charAt(0);
      opers.offer(new Oper(X,C));
    }

    snake.add(new Pos(0, 0)); // 시작 위치
    time = 0; dir = 0;
  }
  public static void main(String[] args) throws Exception {
    init();
    while (!isEnd()) {
      Pos head = snake.get(0);
      Pos next = new Pos(head.r + dy[dir], head.c + dx[dir]);
      snake.add(0,next);
      
      time++;
      if (isEnd()) break;

      if (apples.contains(next)) {
        apples.remove(next);
      } else {
        snake.remove(snake.size() - 1); // 꼬리 제거
      }

      if (!opers.isEmpty() && time == opers.peek().time) {
        Oper oper = opers.poll();
        if (oper.dir == 'D') {
          dir = (dir + 1) % 4;
        } else if (oper.dir == 'L') {
          dir = (dir +3) % 4;
        }
      }
    }

    System.out.println(time);
  }
}
