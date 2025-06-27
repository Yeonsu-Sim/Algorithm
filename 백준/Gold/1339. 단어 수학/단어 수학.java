import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        // 1) 각 문자별 가중치 누적
        long[] weight = new long[26];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            int len = words[i].length();
            long place = 1;
            for (int j = len - 1; j >= 0; j--) {
                int c = words[i].charAt(j) - 'A';
                weight[c] += place;
                place *= 10;
            }
        }

        // 2) 가중치와 문자 인덱스를 쌍으로 리스트에 담아 정렬
        List<Integer> letters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (weight[i] > 0) {
                letters.add(i);
            }
        }
        letters.sort((a, b) -> Long.compare(weight[b], weight[a]));

        // 3) 9부터 순서대로 배정
        int[] assign = new int[26];
        int digit = 9;
        for (int idx : letters) {
            assign[idx] = digit--;
        }

        // 4) 최종 합 계산
        long result = 0;
        for (String w : words) {
            long num = 0;
            for (int j = 0; j < w.length(); j++) {
                num = num * 10 + assign[w.charAt(j) - 'A'];
            }
            result += num;
        }

        System.out.println(result);
    }
}