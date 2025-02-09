import java.io.*;
import java.util.*;

class Main {
    static int N, K, result;
    static int[][] input = new int[6][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        for (int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            int gender = Integer.parseInt(line[0]);
            int grade = Integer.parseInt(line[1]);

            input[grade-1][gender]++;
        }

        for (int i=0; i<6; i++) {
            for (int j=0; j<2; j++) {
                result += (int) Math.ceil((double)input[i][j] / K);
            }
        }

        System.out.println(result);
    }
}