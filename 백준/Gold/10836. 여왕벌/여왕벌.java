import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] col1 = new int[M];
        int[] row1 = new int[M];

        Arrays.fill(col1, 1);
        Arrays.fill(row1, 1);

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cnt0 = Integer.parseInt(st.nextToken());
            int cnt1 = Integer.parseInt(st.nextToken());
            int cnt2 = Integer.parseInt(st.nextToken());

            int idx = cnt0;
            for (int j=0; j<cnt1 && idx<M; j++) col1[idx++]+=1;
            for (int j=0; j<cnt2 && idx<M; j++) col1[idx++]+=2;

            idx = M-1;
            for (int i=0; i<cnt2 && idx>0; i++) row1[idx--]+=2;
            for (int i=0; i<cnt1 && idx>0; i++) row1[idx--]+=1;
        }


        StringBuilder sb = new StringBuilder();

        for (int j=M-1; j>=0; j--) {
            sb.append(col1[j]).append(' ');

            for (int i=1; i<M; i++) {
                sb.append(row1[i]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
