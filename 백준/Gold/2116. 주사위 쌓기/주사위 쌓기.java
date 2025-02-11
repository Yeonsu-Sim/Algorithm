import java.io.*;
import java.util.*;

/**
 * Greedy - 모든 경우 구하면서 Max 찾기
 */
public class Main {

    static int N;
    static int[][] arr;
    static int[] cases = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        N = Integer.parseInt(br.readLine());
        arr = new int[N][6];

        String[] line;
        for (int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            
            int A = Integer.parseInt(line[0]);
            int F = Integer.parseInt(line[5]);
            arr[i][A-1] = F;
            arr[i][F-1] = A;

            int B = Integer.parseInt(line[1]);
            int D = Integer.parseInt(line[3]);
            arr[i][B-1] = D;
            arr[i][D-1] = B;

            int C = Integer.parseInt(line[2]);
            int E = Integer.parseInt(line[4]);
            arr[i][C-1] = E;
            arr[i][E-1] = C;
        }
        
        for (int i=0; i<6; i++) {
            // i가 밑으로 오는 경우
            int bottom = i;
            cases[i] = lineUp(0, bottom);
        }
        
        Arrays.sort(cases);
        System.out.println(cases[cases.length-1]);  // 가장 높은 경우 출력
        
    }

    // bottom이 밑으로 올 때, 가장 높은 수
    static int lineUp (int floor, int bottom) {
        if (floor == N) {
            return 0;
        }

        int top = arr[floor][bottom];
        
        // 위아래 제외 가장 큰수
        int max = 0;
        for (int i=1; i<=6; i++) {
            if (i == bottom+1 || i == top) continue;
            max = Math.max(max, i);
        }
        
        return max + lineUp(floor+1, top-1);
    }
}
