import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<Ball> balls = new ArrayList<>();        
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls.add(new Ball(color, size, i));
        }

        Collections.sort(balls, (Ball a, Ball b) -> a.size - b.size);

        int acc = 0;
        int[] colorAcc = new int[N+1];
        int[] result = new int[N];

        int pointer = 0;
        for (int i=0; i<N; i++) {
            Ball cur = balls.get(i);
            while (balls.get(pointer).size < cur.size) {
                acc += balls.get(pointer).size;
                colorAcc[balls.get(pointer).color] += balls.get(pointer).size;
                pointer++;
            }
            result[cur.idx] = acc - colorAcc[cur.color];
        }

        StringBuilder sb = new StringBuilder();
        for (int r: result) sb.append(r+"\n");
        System.out.print(sb);
    }

    static class Ball {
        int color; int size; int idx;
        Ball(int c, int s, int i) { color = c; size = s; idx = i;}
    }
}