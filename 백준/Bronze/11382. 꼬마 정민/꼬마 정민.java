import java.io.*;

public class Main {
    static long A,B,C;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        A = Long.parseLong(line[0]);
        B = Long.parseLong(line[1]);
        C = Long.parseLong(line[2]);

        System.out.println(A+B+C);
    }
}