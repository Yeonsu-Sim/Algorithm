import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int bWeight = 0;
        int time = 0;

        for (int i=0; i<N; i++) {
            time++;
            
            int next = Integer.parseInt(st.nextToken());
            
            while (true) {
                
                if (!q.isEmpty() && q.peekFirst()[1] == time) {
                    bWeight -= q.pollFirst()[0];
                }

                if (bWeight + next <= L && q.size() < W) break;

                time++;
            }

            q.offerLast(new int[]{next, time+W});
            bWeight += next;
        }

        System.out.println(q.pollLast()[1]);
    }
}
