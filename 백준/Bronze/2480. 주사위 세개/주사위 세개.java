import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int C = Integer.parseInt(input[2]);

        if (A == B && B == C) {
            System.out.println(10000 + A*1000);
        } else if (A == B || C == A) {
            System.out.println(1000 + A * 100);
        } else if (B == C) {
            System.out.println(1000 + B * 100);
        } else {
            System.out.println(Math.max(Math.max(A,B),C) * 100);
        }
    }
}