import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A;
    static Ground[][] grounds;

    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        grounds = new Ground[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                grounds[i][j] = new Ground();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            grounds[r][c].live.add(age);
        }

        for (int year = 0; year < K; year++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(getTotalTree());
    }

    static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Ground g = grounds[i][j];
                Deque<Integer> newLive = new LinkedList<>();
                int nutrients = g.nutrient;

                for (int age : g.live) {
                    if (nutrients >= age) {
                        nutrients -= age;
                        newLive.addLast(age + 1);
                    } else {
                        g.dead.add(age);
                    }
                }

                g.nutrient = nutrients;
                g.live = newLive;
            }
        }
    }

    static void summer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Ground g = grounds[i][j];
                for (int deadAge : g.dead) {
                    g.nutrient += deadAge / 2;
                }
                g.dead.clear();
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : grounds[i][j].live) {
                    if (age % 5 != 0) continue;
                    for (int d = 0; d < 8; d++) {
                        int ni = i + dy[d];
                        int nj = j + dx[d];
                        if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                        grounds[ni][nj].live.addFirst(1); // 어린 나무는 맨 앞에
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grounds[i][j].nutrient += A[i][j];
            }
        }
    }

    static int getTotalTree() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += grounds[i][j].live.size();
            }
        }
        return total;
    }

    static class Ground {
        int nutrient = 5;
        Deque<Integer> live = new LinkedList<>();
        List<Integer> dead = new ArrayList<>();
    }
}
