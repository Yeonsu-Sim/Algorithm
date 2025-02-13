import java.io.*;
import java.util.*;


public class Main {

    static int N, ascIdx,descIdx;
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

        // 증가수열 초기화
        Arrays.fill(asc, 1);
        Arrays.fill(desc, 1);

        for (int i=0; i<N-1; i++) {
            if (arr[i] > arr[i+1]) {
                asc[ascIdx]++;
                descIdx++;
            } else if (arr[i] < arr[i+1]) {
                desc[descIdx]++;
                ascIdx++;
            } else {
                asc[ascIdx]++;
                desc[descIdx]++;
            }

        }

        int ascMax = 0;
        for (int i=0; i<N; i++) {
            ascMax = Math.max(ascMax, asc[i]);
        }

        int descMax = 0;
        for (int i=0; i<N; i++) {
            descMax = Math.max(descMax, desc[i]);
        }

        System.out.println(Math.max(ascMax, descMax));
    }
}
