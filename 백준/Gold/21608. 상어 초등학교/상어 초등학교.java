import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
    static int N, R;
    static int[][] map, emptyScores;
    static int[] order;
    static HashMap<Integer,int[]> likeMap;

    // 사방탐색 델타
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parseInt(br.readLine());
        R = (int) Math.pow(N,2);
        map = new int[N][N];
        emptyScores = new int[N][N];
        order = new int[R];
        likeMap = new HashMap<>();
        
        String[] line;
        // 좋아하는 학생 저장
        for (int r=0; r<R; r++) {
            line = br.readLine().split(" ");

            int student = parseInt(line[0]);
            order[r] = student;
            int[] likes = new int[4];
            for (int j=1; j<=4; j++) {
                likes[j-1] = parseInt(line[j]);
            }
            likeMap.put(student, likes);
        }

        // 빈공간 점수 초기화
        emptyScoresInit();

        // 순차적으로 자리배정
        searchPlace();

        // 만족도 계산
        int total = satisfaction();
        System.out.println(total);
    }

    // 빈공간 점수 초기화
    static void emptyScoresInit() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {

                for (int d=0; d<4; d++) {
                    int x = j + dx[d];
                    int y = i + dy[d];
                    if (!isValid(x,y)) continue;  // 유효 공간 확인
                    if (map[y][x] == 0) emptyScores[y][x]++;
                }

            }
        }
    }
    
    // 순서대로 자리배정
    static void searchPlace() {
        for (int s=0; s<R; s++) {
            int student = order[s];
            
            int row = 0;
            int col = 0;
            int likeMax = -1;
            int emptyMax = -1;

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (map[i][j] != 0) continue;  // 자리가 이미 찼다면 건너뛰기

                    int like = countLike(student, i, j);
                    int empty = emptyScores[i][j];

                    // 문제 요구 사항 조건부
                    if (like < likeMax) continue;
                    if (like == likeMax && empty <= emptyMax) continue;
                    likeMax = like;
                    emptyMax = empty;
                    row = i;
                    col = j;
                }
            }

            // 자리 저장 맵 갱신
            map[row][col] = student;
        
            // emptyScores 갱신
            for (int d=0; d<4; d++) {
                int x = col + dx[d];
                int y = row + dy[d];
                if (!isValid(x, y)) continue;  // 유효 공간 확인
                emptyScores[y][x]--;
            }
        }
    }

    // 좋아하는 사람 수 반환
    static int countLike(int student, int i, int j) {
        int likeScore = 0;
        for (int d=0; d<4; d++) {
            int x = j + dx[d];
            int y = i + dy[d];
            if (!isValid(x, y)) continue;  // 유효 공간 확인
            
            for (int s: likeMap.get(student)) {
                if (s != map[y][x]) continue;
                likeScore++;
                break;
            }

        }
        return likeScore;
    }

    // 만족도 계산 (10^n)
    static int satisfaction() {
        int sum = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int student = map[i][j];
                int score = (int) Math.pow(10, countLike(student, i, j)-1);
                sum += score;
            }
        }
        return sum;
    }

    // 유효 범위 확인
    static boolean isValid(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }
}
