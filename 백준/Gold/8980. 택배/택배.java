import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        PriorityQueue<Box> pq = new PriorityQueue<>(
            (a, b) -> (a.to == b.to) ? a.from - b.from : a.to - b.to
        );
        
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            pq.offer(new Box(from, to, to-from, cnt));
        }
        
        int[] truck = new int[N-1];
        int sum = 0;
        while (!pq.isEmpty()) {
            Box cur = pq.poll();
            int stuck = cur.cnt;
            for (int i=cur.from-1; i<cur.to-1; i++) {
                stuck = Math.min(stuck, C - truck[i]);
            }

            for (int i=cur.from-1; i<cur.to-1; i++) truck[i]+=stuck;
            sum += stuck;
        }

        System.out.println(sum);
    }

    static class Box {
        int from; int to; int length; int cnt;
        Box(int from, int to, int length, int cnt) {
            this.from = from;
            this.to = to;
            this.length = length;
            this.cnt = cnt;
        }
    }
}