import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int answer = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!pq.isEmpty() && pq.peek() > y) {
                pq.poll();
                answer++;
            }
            if (y == 0) continue;
            if (pq.isEmpty() || pq.peek() < y) pq.offer(y);
        }

        answer += pq.size();

        System.out.println(answer);
    }
}