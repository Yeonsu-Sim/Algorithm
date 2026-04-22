import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[][] deltas = {
        {},
        {1,-1}, {1,0}, {1,1},
        {0,-1},{0,0},{0,1},
        {-1,-1},{-1,0},{-1,1}
    };
    static Pos posI;
    static List<Pos> rs = new ArrayList<>();
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                char c = line.charAt(j);
                if (c == 'R') {
                    rs.add(new Pos(i,j));
                    set.add(i+","+j);
                } else if (c == 'I') {
                    posI = new Pos(i,j);
                }
            }
        }

        String commands = br.readLine();
        int cIdx = 0;
        boolean kraj = false;
        while (cIdx < commands.length()) {
            int command = Integer.parseInt(commands.charAt(cIdx)+"");
            if (!moveI(command) || !moveR()) {
                kraj = true; break;
            }
            cIdx++;
        }

        if (kraj) {
            System.out.println("kraj "+(cIdx+1));
        } else {
            printMap();
        }
    }

    public static boolean moveI(int command) {
        int ni = posI.i + deltas[command][0];
        int nj = posI.j + deltas[command][1];

        // 게임 종료 확인
        if (set.contains(ni+","+nj)) return false;

        posI = new Pos(ni,nj);
        return true;
    }

    public static boolean moveR() {
        List<Pos> nextRs = new ArrayList<>();
        HashSet<String> nextSet = new HashSet<>();
        HashMap<String, Integer> bombCheck = new HashMap<>();

        // 게임 종료 확인
        for (Pos r : rs) {
            int nextDelta = closer(r.i, r.j);
            int ni = r.i + deltas[nextDelta][0];
            int nj = r.j + deltas[nextDelta][1];

            if (posI.i == ni && posI.j == nj) return false;

            String key = ni+","+nj;
            bombCheck.putIfAbsent(key, 0);
            bombCheck.put(key, bombCheck.get(key)+1);
        }

        for (String key : bombCheck.keySet()) {
            int cnt = bombCheck.get(key);
            String[] ij = key.split(",");
            int ri = Integer.parseInt(ij[0]);
            int rj = Integer.parseInt(ij[1]);

            if (cnt == 1) {
                nextRs.add(new Pos(ri, rj));
                nextSet.add(key);
            }
        }

        rs = nextRs;
        set = nextSet;

        return true;
    }

    public static int closer(int i, int j) {
        int delta = 0;
        int minDist = Integer.MAX_VALUE;
        for (int d=1; d<10; d++) {
            int ni = i + deltas[d][0];
            int nj = j + deltas[d][1];

            int dist = Math.abs(posI.i - ni) + Math.abs(posI.j - nj);
            if (dist < minDist) {
                minDist = dist;
                delta = d;
            }
        }

        return delta;
    }

    public static void printMap() {
        char[][] map = new char[N][M];
        for (char[] m : map) Arrays.fill(m, '.');

        map[posI.i][posI.j] = 'I';
        for (Pos r : rs) map[r.i][r.j] = 'R';

        for (char[] m : map) {
            for (char c : m) System.out.print(c);
            System.out.println();
        }
    }

    public static class Pos {
        int i; int j;
        Pos(int i, int j ) { this.i = i; this.j = j; }
    }
}