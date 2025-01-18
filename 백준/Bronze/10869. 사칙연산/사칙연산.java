import java.io.*;
import java.util.*;


public class Main {
    static int A, B, result;
    static int[] in;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        A = Integer.parseInt(line[0]);
        B = Integer.parseInt(line[1]);

        System.out.println(A+B);
        System.out.println(A-B);
        System.out.println(A*B);
        System.out.println(A/B);
        System.out.println(A%B);
    }
}