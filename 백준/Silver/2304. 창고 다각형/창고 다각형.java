import java.io.*;
import java.util.*;

public class Main {
    static int N, L, H, area;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        String[] line;
        for (int n=0; n<N; n++) {
            line = br.readLine().split(" ");
            arr[n][0] = Integer.parseInt(line[0]);
            arr[n][1] = Integer.parseInt(line[1]);
        }

        // for (int i=0; i<N; i++) {
        //     for (int j=i+1; j<N; j++) {
        //         int[] temp;
        //         if (arr[i][0] > arr[j][0]) {
        //             temp = arr[i];
        //             arr[i] = arr[j];
        //             arr[j] = temp;
        //         }
        //     }
        // }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 최대 기둥 인덱스 구하기
        int maxL = 0;
        int maxH = 0;
        int maxIdx = 0;
        for (int i=0; i<N; i++) {
            if (arr[i][1] >= maxH) {
                maxL = arr[i][0];
                maxH = arr[i][1];
                maxIdx = i;
            }
        }
        
        // 왼쪽 면적 구하기
        L = arr[0][0];
        H = arr[0][1];
        for (int n=1; n<=maxIdx; n++) {
            // Max값 찾기
            if (arr[n][1] <= H) continue;
            area += H*(arr[n][0]-L);
            L = arr[n][0];
            H = arr[n][1];
        }

        // 피크 면적 구하기
        area += H*(maxL-L+1);


        // 오른쪽 면적 구하기
        L = arr[N-1][0];
        H = arr[N-1][1];
        for (int n=N-2; n>=maxIdx; n--) {
            // Max값 찾기
            if (arr[n][1] <= H) continue;
            area += H*(L-arr[n][0]);
            L = arr[n][0];
            H = arr[n][1];

        }
        
        System.out.println(area);
    }

}