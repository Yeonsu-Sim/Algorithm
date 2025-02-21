import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dwarf = new int[9];
        for (int i=0; i<9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
        }

        comb(0, 0, 0, dwarf, new int[7]);
    }

    static void comb(int count, int start, int sum, int[] dwarf, int[] out) {
        if (sum > 100) return;

        if (count == 7) {
            if (sum == 100) {
                for (int i=0; i<7; i++) {
                    System.out.println(out[i]);
                }
            }
            return;
        }

        for (int i=start; i<9; i++) {
            out[count] = dwarf[i];
            comb(count+1, i+1, sum+dwarf[i], dwarf, out);
        }
    }
}
