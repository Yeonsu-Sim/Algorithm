import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        List<int[]> stars = new ArrayList<>();
        for (int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (!xs.contains(x)) xs.add(x);
            if (!ys.contains(y)) ys.add(y);

            stars.add(new int[]{x,y});
        }

        Collections.sort(xs);
        Collections.sort(ys);

        int max = 0;
        for (int x : xs) {
            for (int y : ys) {
                int kick = 0;
                for (int[] star : stars) {
                    if (x <= star[0] && star[0] <= x+L 
                        && y <= star[1] && star[1] <= y +L) kick++;
                }
                max = Math.max(max, kick);
            }
        }

        System.out.println(K - max);

    }
}