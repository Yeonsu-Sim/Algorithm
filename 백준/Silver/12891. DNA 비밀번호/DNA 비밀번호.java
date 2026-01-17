import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String letters = br.readLine();

        int[] target = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<4; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int cnt = 0;
        int[] pattern = new int[4];

        while (true) {
            int right = q.peekLast();
            if (right == S) break;
            pattern[getIdx(letters.charAt(right))]++;

            if (q.size() == P) {
                if (isFulfill(target, pattern)) cnt++;

                int left = q.pollFirst();
                pattern[getIdx(letters.charAt(left))]--;
            }

            q.offerLast(right+1);
        }

        System.out.println(cnt);

    }

    static boolean isFulfill(int[] a, int[] b) {
        for (int i=0; i<a.length; i++) {
            if (a[i] > b[i]) return false;
        }
        return true;
    }

    static int getIdx(char c) {
        switch (c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
        }
        return -1;
    }
}