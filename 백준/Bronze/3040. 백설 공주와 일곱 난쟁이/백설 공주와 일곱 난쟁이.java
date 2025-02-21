import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dwarf = new int[9];
        int target = 0;
        for (int i=0; i<9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
            target += dwarf[i];
        }

        comb(0, 0, target-100, 0, dwarf, new int[2]);
    }

    static void comb(int count, int start, int target, int sum, int[] dwarf, int[] out) {
        if (sum > target) return;

        if (count == 2) {
            if (sum == target) {
                for (int i=0; i<9; i++) {
                    if (out[0] == i || out[1] == i) continue;
                    System.out.println(dwarf[i]);
                }
            }
            return;
        }

        for (int i=start; i<9; i++) {
            out[count] = i;
            comb(count+1, i+1, target, sum+dwarf[i], dwarf, out);
        }
    }
}
