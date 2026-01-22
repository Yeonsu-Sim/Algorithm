import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        if (N == 1) {
            System.out.println('A');
            return;
        } else if (N == 2) {
            int x1 = arr[0];
            int y1 = arr[1];

            if (x1 == y1) System.out.println(x1);
            else System.out.println('A');
            return;
        }

        // 3개 이상 = 2점 이상 주어진 경우 -> 하나의 선만 만들 수 있음
        // y = ax + b
        int a = 1; int b = 0;
        int x1 = arr[0]; int y1 = arr[1];
        int x2 = arr[1]; int y2 = arr[2];

        if (x1 != x2) {
            a = (y1-y2)/(x1-x2);
            b = y1 - x1*a;
        }

        for (int i=1; i<N-1; i++) {
            int x = arr[i];
            int y = arr[i+1];
            if (y == x*a + b) continue;

            System.out.println('B');
            return;
        }

        System.out.println(arr[N-1]*a+b);
    }
}