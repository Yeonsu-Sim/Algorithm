import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a + b > N+1) {
            System.out.println(-1);
            return;
        }

        int max = Math.max(a,b);
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(max);
        for (int i=a-1; i>0; i--) q.offerFirst(i);
        for (int i=b-1; i>0; i--) q.offerLast(i);
        
        // 건물 메꾸기
        if (a == 1) {
            q.pollFirst();
            while (q.size() < N-1) q.offerFirst(1);
            q.offerFirst(max);
        } else {
            while (q.size() < N) q.offerFirst(1);
        }
        
        StringBuilder sb = new StringBuilder();
        // 방향 맞게 출력
        while (!q.isEmpty()) sb.append(q.poll()+" ");

        System.out.print(sb);
    }
}