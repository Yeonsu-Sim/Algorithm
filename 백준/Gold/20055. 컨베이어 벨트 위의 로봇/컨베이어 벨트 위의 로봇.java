import java.util.*;
import java.io.*;

public class Main {
  static class Conveyor {
    int hp;
    boolean hasRobot;
    public Conveyor(int hp) {
      this.hp = hp;
    }
    @Override
    public String toString() {
      return "hp: "+hp+" | robot: "+(hasRobot?'o':'x');
    }
  }

  static int N, K, in, out;
  static List<Conveyor> belt = new ArrayList<>();
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    in = 0;
    out = N-1;

    st = new StringTokenizer(br.readLine());
    for (int i=0; i<N*2; i++) {
      int hp = Integer.parseInt(st.nextToken());
      belt.add(new Conveyor(hp));
    }

    int count = 0;
    while (K > 0) {
      rotate();
      takeRobot();

      move();
      // if (K <= 0) break; 와우.. count++은 해줘야 하니까..
      takeRobot();

      putRobot();
      count++;
    }

    System.out.println(count);
  }

  static void rotate() {
    // in, out 이동
    in = ((in-1)+2*N)%(2*N);
    out = ((out-1)+2*N)%(2*N);
  }

  static void move() {
    if (in < out) {
      int o = out-1;
      while(o >= in && K > 0) { moveRobot(o--); }

    } else {
      int i = 2*N-1;
      int o = out-1;
      while (o >= 0 && K > 0) { moveRobot(o--); }
      while ( i >= in && K > 0) { moveRobot(i--); }
    }
  }

  static void moveRobot(int i) {
    Conveyor cur = belt.get(i);
    Conveyor next = belt.get((i+1)%(2*N));  

    if (cur.hasRobot && !next.hasRobot && next.hp > 0) {
      next.hasRobot = true;
      if (--next.hp == 0) K--;
      cur.hasRobot = false;
    }
  }

  static void putRobot() {
    Conveyor c = belt.get(in);
    if(c.hp > 0) {
      if (--c.hp == 0) K--;
      c.hasRobot = true;
    }
  }

  static void takeRobot() {
    Conveyor c = belt.get(out);
    if (c.hasRobot) {
      c.hasRobot = false;
    }
  }
}
