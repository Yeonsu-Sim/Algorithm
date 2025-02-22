import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

    static int N, M, count;
    static int[][][] visited;
    
    static int[] dx = {0,-1,0,1};  // 상좌하우
    static int[] dy = {-1,0,1,0}; 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");

        N = parseInt(NM[0]);
        M = parseInt(NM[1]);
        List<int[]> airconList = new ArrayList<>();

        int[][] input = new int[N][M];
        visited = new int[N][M][4];  // 상좌하우
        String[] line;
        for (int n=0; n<N; n++) {
            line = br.readLine().split(" ");
            for (int m=0; m<M; m++) {
                int num = parseInt(line[m]);
                if (num == 9) {
                    airconList.add(new int[] {n,m});
                }
                input[n][m] = num;
            }
        }

        for (int[] aircon : airconList) {
            int row = aircon[0];
            int col = aircon[1];
            for (int d=0; d<4; d++) {  // 상좌하우 탐색
                dfs(d, row, col, input);
            }
        }
        System.out.println(count);
    }

    static void dfs(int prevDelta, int row, int col, int[][] input) {

        if (visited[row][col][prevDelta] == 1) {  // 같은 방향으로 탐색한 적이 있다면 종료
            return;
        }

        // 방문한 적이 없을 때만 갯수 세기
        boolean isVisited = false;
        for (int v=0; v<4; v++) {
            if (visited[row][col][v] == 1) {
                isVisited = true;
                break;
            }
        }
        if (!isVisited) count++;

        visited[row][col][prevDelta] = 1;

        int newDelta = prevDelta;
        switch (input[row][col]) {
            case 0: 
                if (visited[row][col][(prevDelta+2)%4] == 1) {
                    return;
                }
                break;
            case 1:
                if (prevDelta%2 == 1) {  // 좌우 라면
                    return;
                }
                if (visited[row][col][(prevDelta+2)%4] == 1) {
                    return;
                }
                break;
            case 2:
                if (prevDelta%2 == 0) {  // 상하 라면
                    return;
                }
                if (visited[row][col][(prevDelta+2)%4] == 1) {
                    return;
                }
                break;
            case 3:
                newDelta = Math.abs(newDelta-3);
                break;
            case 4:
                if (prevDelta == 0 || prevDelta == 1) {
                    newDelta = Math.abs(newDelta-1);
                } else {
                    newDelta = Math.abs(newDelta-5);
                }
                break;
            default:  // 에어컨인 경우
                break;
        }

        int x = col+dx[newDelta];
        int y = row+dy[newDelta];
        if (!isValid(y, x)) return;

        dfs(newDelta, y, x, input);
    }

    static boolean isValid(int y, int x) {
        return y >=0 && y < N && x >=0 && x < M;
    }
}
