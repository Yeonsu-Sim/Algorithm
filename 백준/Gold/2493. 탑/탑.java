import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] buildings = new int[N+1];
        for (int i=1; i<=N; i++) buildings[i] = Integer.parseInt(st.nextToken());

        int[] receivers = new int[N+1];
        for (int i=2; i<=N; i++) {
            int j = i-1;

            // 해당 건물보다 큰지
            while (j != 0 && buildings[j] <= buildings[i]) {
                j = receivers[j];
            }

            // 해당 건물보다 작으면 그 건물이 수신
            receivers[i] = j;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) sb.append(receivers[i]+" ");
        System.out.print(sb);
    }
}