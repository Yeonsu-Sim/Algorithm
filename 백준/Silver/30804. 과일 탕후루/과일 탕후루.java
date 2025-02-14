import java.io.*;
import java.util.*;

public class Main {
    static int N, count, kind, start1, start2, end1, end2;
    static int[] arr;
    static boolean[] exist = new boolean[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        String[] line = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(line[i]);
            arr[i] = num;
            exist[num-1] = true;
        }

        int kind=0;
        for (int i=0; i<9; i++) {
            if (exist[i]) kind++;
        }
        if (kind <= 2) {
            System.out.println(N);
            return;
        }

        move(arr[start1], arr[start1]);
        
        System.out.println(count);
    }

    static void move(int prevNum, int lastNum) {
        if(end2 == N-1) return;
        for (int i=start1+1; i<N; i++) {
            if (arr[i] != prevNum) {
                lastNum = arr[i];
                end1 = i-1;
                start2 = i;
                end2 = i;
                break;
            }
        }

        for (int i=start2+1; i<N; i++) {
            if (arr[i] == prevNum) {
                prevNum = lastNum;
                lastNum = arr[i];
                start2 = i;
                end2 = i;
                continue;
            }
            if (arr[i] != lastNum) {
                end2 = i-1;
                break;
            }
            end2 = i;
        }
        count = Math.max(count, end2-start1+1);

        start1 = start2;
        end1 = end2;
        move(lastNum, lastNum);
    }
}
