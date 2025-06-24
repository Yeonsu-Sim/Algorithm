import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);                     // 1. 정렬

        int L = 0, R = N - 1;                 // 2. 투 포인터
        int best = Integer.MAX_VALUE;
        int a = 0, b = 0;

        while (L < R) {
            int sum = arr[L] + arr[R];

            if (Math.abs(sum) < best) {       // 최적값 갱신
                best = Math.abs(sum);
                a = arr[L];
                b = arr[R];
                if (best == 0) break;         // 더 나아질 수 없음
            }

            if (sum > 0)      R--;            // 합이 양수 → 오른쪽 줄이기
            else              L++;            // 합이 음수 → 왼쪽 늘리기
        }

        System.out.println(a + " " + b);      // 3. 출력 (이미 정렬된 상태)
    }
}
