import java.util.*;
import java.io.*;

public class Main {

    static int N, M, G, R, answer;
    static int[][] map;
    static List<Pos> base = new LinkedList<>();

    static class Pos {
        int i, j;
        Pos(int i, int j) { this.i = i; this.j = j; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;

                if (n == 2) {
                    base.add(new Pos(i,j));
                }
            }
        }

        comb(0, new int[base.size()], 0, 0);
        System.out.println(answer);
    }

    public static void comb(int depth, int[] out, int gCnt, int rCnt) {
        if (depth == base.size()) {
            if (gCnt == G && rCnt == R) {
                simulate(out);
            }
            return;
        }

        if (base.size() - depth < R - rCnt + G - gCnt) return;

        out[depth] = 0;
        comb(depth+1, out, gCnt, rCnt);
        if (gCnt < G) {
            out[depth] = 1;
            comb(depth+1, out, gCnt+1, rCnt);
        }
        if (rCnt < R ) {
            out[depth] = 2;
            comb(depth+1, out, gCnt, rCnt+1);
        }
    }

    static int[] dy = new int[]{0, 0, -1, 1};
    static int[] dx = new int[]{-1, 1, 0, 0};
    // out: 0(없음) | 1(G) | 2(R)
    // map: 0(호수) | 1(땅) | 2(베이스) | 3(G) | 4(R) | 5(꽃)
    public static void simulate(int[] out) {
        int fCnt = 0;

        int[][] tmp = new int[N][];
        for (int t=0; t<N; t++) tmp[t] = Arrays.copyOf(map[t], M);

        Queue<Pos> q = new ArrayDeque<>();
        for (int o=0; o<out.length; o++) {
            if (out[o] == 0) continue;
            Pos pos = base.get(o);
            tmp[pos.i][pos.j] = 2 + out[o];
            q.offer(pos);
        }

        while (!q.isEmpty()) {
            // spread: 3(G) | 4(R) | 5(꽃)
            HashMap<String, Integer> spread = new HashMap<>();
            
            int size = q.size();
            while (size-- > 0) {
                Pos cur = q.poll();
                int value = tmp[cur.i][cur.j];
                
                for (int d=0; d<4; d++) {
                    int ni = cur.i + dy[d];
                    int nj = cur.j + dx[d];
    
                    if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                    if (tmp[ni][nj] < 1 || tmp[ni][nj] > 2) continue; // 땅이 아닐 때
                    
                    String key = ni+","+nj;
                    Integer s = spread.get(key);

                    if (s == null) spread.put(key, value);
                    else if (s != value && s != 5) spread.put(key, 5);
                }
            }

            for (String key : spread.keySet()) {
                int s = spread.get(key);

                String[] pos = key.split(",");
                int i = Integer.parseInt(pos[0]);
                int j = Integer.parseInt(pos[1]);
                tmp[i][j] = s;

                if (s < 5) q.offer(new Pos(i,j));
                else fCnt++;  // 꽃 폈을 때
            }
        }

        answer = Math.max(answer, fCnt);
    }
}