import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i=0; i<N; i++) {
            int left = 0;
            int right = N-1;
            int target = arr[i];
            while (left < right) {
                int sum = arr[left] + arr[right];

                if (target == sum) {
                    if (i == left) {
                        left++;
                        continue;
                    } else if (i == right) {
                        right--;
                        continue;
                    }
                    cnt++;
                    break;
                }

                if (target < sum) right--;
                else if (target > sum) left++;
            }
        }

        System.out.println(cnt);
    }
}