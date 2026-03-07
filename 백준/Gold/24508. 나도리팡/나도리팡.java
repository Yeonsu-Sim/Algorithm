import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        long cnt = 0;
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
            cnt+=a;
        }
        if (cnt%K != 0) {
            System.out.println("NO");
            return;
        }

        Arrays.sort(arr);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            if (arr[i] == 0) continue;
            q.offerLast(arr[i]);
        }

        long answer = 0;
        while (!q.isEmpty()) {
            if (answer > T) break;

            int smallest = q.pollFirst();
            int biggest = q.pollLast();

            int need = K - biggest;
            int move = smallest > need ? need : smallest;
            smallest -= move;
            biggest += move;
            answer += move;

            if (smallest > 0) q.offerFirst(smallest);
            if (biggest < K) q.offerLast(biggest);
        }
        System.out.println(answer<=T? "YES":"NO");
    }
}