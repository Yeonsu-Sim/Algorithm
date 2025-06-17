import java.io.*;
import java.util.*;

public class Main {
    static class Jam implements Comparable<Jam> {
        int weight, value;
        Jam(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jam o) {
            return this.weight - o.weight; // 무게 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 보석 수
        int K = Integer.parseInt(st.nextToken()); // 가방 수

        Jam[] jams = new Jam[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jams[i] = new Jam(weight, value);
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jams); // 보석 무게 기준 정렬
        Arrays.sort(bags); // 가방 무게 기준 정렬

        PriorityQueue<Jam> pq = new PriorityQueue<>((a, b) -> b.value - a.value); // 가치 높은 순
        long totalValue = 0;
        int jamIdx = 0;

        for (int i = 0; i < K; i++) {
            int capacity = bags[i];

            // 현재 가방에 넣을 수 있는 보석들 PQ에 추가
            while (jamIdx < N && jams[jamIdx].weight <= capacity) {
                pq.offer(jams[jamIdx++]);
            }

            // 가장 가치 높은 보석을 가방에 담기
            if (!pq.isEmpty()) {
                totalValue += pq.poll().value;
            }
        }

        System.out.println(totalValue);
    }
}
