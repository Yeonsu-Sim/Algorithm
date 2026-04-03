import java.io.*;
import java.util.*;

public class Main {
    static class Building {
        int idx;
        int height;

        Building(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftCnt = new int[N + 1];
        int[] rightCnt = new int[N + 1];
        int[] leftNear = new int[N + 1];
        int[] rightNear = new int[N + 1];

        Deque<Building> stack = new ArrayDeque<>();

        // 왼쪽 보기
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek().height <= arr[i]) {
                stack.pop();
            }

            leftCnt[i] = stack.size();
            if (!stack.isEmpty()) {
                leftNear[i] = stack.peek().idx;
            }

            stack.push(new Building(i, arr[i]));
        }

        stack.clear();

        // 오른쪽 보기
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek().height <= arr[i]) {
                stack.pop();
            }

            rightCnt[i] = stack.size();
            if (!stack.isEmpty()) {
                rightNear[i] = stack.peek().idx;
            }

            stack.push(new Building(i, arr[i]));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            int total = leftCnt[i] + rightCnt[i];

            if (total == 0) {
                sb.append(0).append('\n');
                continue;
            }

            int ansIdx;
            if (leftNear[i] == 0) {
                ansIdx = rightNear[i];
            } else if (rightNear[i] == 0) {
                ansIdx = leftNear[i];
            } else {
                int leftDist = i - leftNear[i];
                int rightDist = rightNear[i] - i;

                if (leftDist <= rightDist) ansIdx = leftNear[i];
                else ansIdx = rightNear[i];
            }

            sb.append(total).append(' ').append(ansIdx).append('\n');
        }

        System.out.print(sb);
    }
}