import java.io.*;

public class Main {
    static int A,B;

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());

        int sum = 0;
        int cnt = 0;
        while (true) {
            int value = A*(B%10);
            sum += value * Math.pow(10,cnt++);
            System.out.println(value);
            B /= 10;
            if (B <= 0) {
                break;
            }
        }
        System.out.println(sum);
    }
}