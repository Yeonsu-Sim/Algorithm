import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+2];
        arr[1] = 1; arr[2] = 2;
        System.out.println(dp());
    }
    static int dp() {
        for (int i = 3; i <= N; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 15746;
        }
        return arr[N];
    }
}
    