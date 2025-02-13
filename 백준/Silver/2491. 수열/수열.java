import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr, asc, desc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);

        arr = new int[N];
        asc = new int[N];
        desc = new int[N];
        line = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        // 수열 길이 초기화
        Arrays.fill(asc, 1);
        Arrays.fill(desc, 1);

        // 증가 수열 길이 누적
        for (int i=1; i<N; i++) {
            if (arr[i] >= arr[i-1]) {
                asc[i] = asc[i-1]+1;
            }
        }

        // 감소 수열 길이 누적
        for (int i=1; i<N; i++) {
            if (arr[i] <= arr[i-1]) {
                desc[i] = desc[i-1]+1;
            }
        }

        int max1=0, max2=0;
        for (int i=0; i<N; i++) {
            max1 = max1 > asc[i] ? max1 : asc[i];
            max2 = max2 > desc[i] ? max2 : desc[i];
        }

        System.out.println(Math.max(max1, max2));
    }
}
