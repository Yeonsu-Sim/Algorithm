import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int A = Integer.parseInt(line[0]);
        int B = Integer.parseInt(line[1]);

        if (A < B) {
            System.out.println("<");
        } else if (A > B) {
            System.out.println(">");
        } else {
            System.out.println("==");
        }
    }
}