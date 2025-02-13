import java.io.*;
import java.util.*;

public class Main {
    static int N, x, y, w, h, area;
    static int[][] arr = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // 각 색종이의 위치와 크기 입력받고 저장
        for (int n=0; n<N; n++) {
            String[] line = br.readLine().split(" ");
            for (int i=0; i<4; i++) {
                x = Integer.parseInt(line[0]);
                y = Integer.parseInt(line[1]);
                w = Integer.parseInt(line[2]);
                h = Integer.parseInt(line[3]);
            }

            for (int i=0; i<h; i++) {
                for (int j=0; j<w; j++) {
                    arr[y+i][x+j] = n+1;
                }
            }
        }
        
        // 각 색종이의 가시 면적 구하기
        for (int n=0; n<N; n++) {
            for (int i=0; i<1001; i++) {
                for (int j=0; j<1001; j++) {
                    if (arr[i][j] == n+1) {
                        area++;
                    }
                }
            }
            sb.append(area).append('\n');
            area = 0;
        }

        System.out.println(sb);
    }
}