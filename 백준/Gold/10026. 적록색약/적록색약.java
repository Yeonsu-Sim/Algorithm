import java.io.*;
import java.util.*;

public class Main {
    static int N, can, cannot;
    static char[][] in;
    static boolean[][] visited;

    static int delta[][] = {{0,-1}, {0,1}, {-1,0}, {1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        in = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                in[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    search(j, i, in[i][j]);
                    can++;
                }
            }
        }
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    searchCannot(j, i, in[i][j]);
                    cannot++;
                }
            }
        }

        System.out.println(can + " " + cannot);
    }

    static void search(int x, int y, char c) {
        visited[y][x] = true;
        int row;
        int col;
        for (int i = 0; i < delta.length; i++) {
            col = x + delta[i][0];
            row = y + delta[i][1];

            if (col >= 0 && col<N && row >=0 && row<N) {
                if (visited[row][col]) {
                    continue;
                }
                if (in[row][col] == c) {
                    search(col, row, c);
                }
            }
        }
    }

    static void searchCannot(int x, int y, char c) {
        visited[y][x] = true;
        int row;
        int col;
        for (int i = 0; i < delta.length; i++) {
            col = x + delta[i][0];
            row = y + delta[i][1];

            if (col >= 0 && col<N && row >=0 && row<N) {
                if (visited[row][col]) {
                    continue;
                }
                if (in[row][col] == c) {
                    searchCannot(col, row, c);
                }
                else if ((in[row][col] == 'G' && c == 'R' ) || ( in[row][col] == 'R' && c == 'G')) {
                    searchCannot(col, row, c);
                }
            }
        }
    }
}
    