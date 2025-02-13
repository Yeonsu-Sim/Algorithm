import java.io.*;
import java.util.*;

/**
 * 방향보다는, 'ㄱ' 모양의 넓이를 구하는 데 초점을 맞춤
 * ㄱ 넓이 = 큰 작사각형 넓이 - 작은 직사각형 넓이
 * 주의) 문제 조건에서, 최악의 경우 (500x500-499x499)x20
 * => int형 범위를 넘어갈 수도 있음
 */

public class Main {
    static int N, bigW, bigH, smallW, smallH;
    static long area;
    static int arr[] = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] line;
        for (int i=0; i<6; i++) {
            line = br.readLine().split(" ");

            arr[i] = Integer.parseInt(line[1]);
        }

        // Max 값 찾기 -> 큰 직사각형의 변 하나 찾기
        int bigWidx = 0;
        for (int i=1; i<6; i++) {
            bigWidx = arr[i] > arr[bigWidx] ? i : bigWidx;
        }
        bigW = arr[bigWidx];

        // 양옆의 두 변중 하나는 무조건 큰 직사각형의 나머지 변
        int prevIdx = (bigWidx+5)%6;
        int nextIdx = (bigWidx+1)%6;
        int bigHidx = arr[prevIdx] > arr[nextIdx] ? prevIdx : nextIdx;

        bigH = arr[bigHidx];

        // 큰 직사각형 변들 양옆에 작은 직사각형 변 길이 유추 가능한 변 존재
        int smallWIdx = 0;
        int smallHIdx = 0;
        if (bigWidx == (bigHidx+5)%6) {  // bigW가 bigH보다 먼저 주어졌을 때
            smallWIdx = (bigHidx+1)%6;
            smallHIdx = (bigWidx+5)%6;
            
            smallW = bigW - arr[smallWIdx];
            smallH = bigH - arr[smallHIdx];
        } else {
            smallWIdx = (bigHidx+5)%6;
            smallHIdx = (bigWidx+1)%6;
            
            smallW = bigW - arr[smallWIdx];
            smallH = bigH - arr[smallHIdx];
        }

        area = (long)bigW*bigH - (long)smallW*smallH;
        area *= (long)N;

        // ㄱ 넓이 출력
        System.out.println(area);
    }
}
 