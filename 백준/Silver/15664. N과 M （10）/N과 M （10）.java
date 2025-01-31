import java.io.*;
import java.util.*;

public class Main {
    static int N, M, length;
    static int[] input, output, arr, count;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        input = new int[N];
        output = new int[N];  // 한 자리씩 탐색 할 때, indexout error 방지지
        arr = new int[N];
        count = new int[N];

        // 입력 저장
        line = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            input[i] = Integer.parseInt(line[i]);
        }

        // 오름차순 정렬
        Arrays.sort(input);
        arr[length] = input[0];
        count[length]++;
        length++;

        // 중복 제거 저장
        for (int i=1; i<input.length; i++) {
            boolean dup = false;
            for (int j=0; j<length; j++) {
                if (arr[j] == input[i]) {
                    count[j]++;
                    dup = true;
                    break;
                }
            }
            if (dup) continue;
            arr[length] = input[i];
            count[length]++;
            length++;
        }
        dfs(0, 0);

    }

    static void dfs(int depth, int start) {
        if (depth == M) {
            for (int i=0; i<M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        }

        for (int i=start; i<length; i++) {
            if (count[i] > 0) {
                output[depth] = arr[i];
                count[i]--;
                dfs(depth+1, i);
                count[i]++;
            }
        }
    }
}