import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/*
 * CLUE; n개의 무게추를 순서를 매겨 저울에 매달기
 *  -> 순열 + 조합 -> 재귀 이용
 * ISSUE: 시간 초과 헨들링
 *  -> 가지치기 진행 : 남은 무게추와 right의 합이 left보다 작을 때에는 더이상 탐색 중단
 *   -> 바로 n!*2^n 더해줌
 */

public class Solution {
    static int T, N, answer;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = parseInt(br.readLine());
        for (int t=1; t<=T; t++) {

            N = parseInt(br.readLine());
            arr = new int[N];
            visited = new boolean[N];
            int rest = 0;
            String[] line = br.readLine().split(" ");
            for (int n=0; n<N; n++) {
                arr[n] = parseInt(line[n]);
                rest += arr[n];
            }

            // 백트래킹 시작
            dfs(0, 0, 0, rest);

            System.out.println("#"+t+" "+answer);
            answer=0;
        }
    }

    // 순열과 조합을 동시해 탐색
    static void dfs(int depth, int left, int right, int rest) {
        if (left < right) {  // 왼쪽보다 오른쪽이 더 무거울 경우 탐색 종료
            return;  // 재귀 종료
        }

        if (depth == N) {  // 모든 무게추를 무사히 저울에 달았다면
            answer++;  // 경우의수에 더하기
            return;  // 재귀 종료
        }

        if (left >= right+rest) {  // 나머지를 모두 오른쪽에 더해도 왼쪽이 큰 경우
            answer += (1 << (N-depth))*factorial(N-depth);  // 이하 모든 탐색이 조건을 만족
            return;  // 재귀 종료
        }

        for (int i=0; i<N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth+1, left, right+arr[i], rest-arr[i]);  // 오른쪽에 담는 경우 탐색
                dfs(depth+1, left+arr[i], right, rest-arr[i]);  // 왼쪽에 담는 경우 탐색
                visited[i] = false;
            }           
        }
    }

    // n! 구하는 함수
    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return factorial(n-1)*n;
    }

}