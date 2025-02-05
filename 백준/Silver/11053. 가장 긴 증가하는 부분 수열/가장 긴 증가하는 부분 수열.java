import java.io.*;
import java.util.*;

class Main {
    static int N, answer;
    static int[] size, input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        size = new int[N];
        input = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            input[i] = Integer.parseInt(line[i]);
        }
        Arrays.fill(size, 1);

        for (int i=0; i<N; i++) {
            for (int j=0; j<i; j++) {
                if (input[i] > input[j]) {
                    size[i] = Math.max(size[i], size[j]+1);
                }
            }
        }

        for (int i=0; i<N; i++) {
            answer = Math.max(answer, size[i]);
        }
        System.out.println(answer);
    }
}