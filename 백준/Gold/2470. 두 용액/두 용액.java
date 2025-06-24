import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int n1, n2, sum;                       // 두 용액과 그 합의 |절댓값|
        Pair(int n1, int n2, int sum) { this.n1 = n1; this.n2 = n2; this.sum = sum; }
        @Override public int compareTo(Pair o) { return Integer.compare(this.sum, o.sum); }
    }

    static void process(int cur,
                        NavigableSet<Integer> same,
                        NavigableSet<Integer> opp,
                        PriorityQueue<Pair> pq) {

        Integer sameMate = same.comparator() == null            // 오름차순(poses)
                             ? same.higher(cur)                 // cur 보다 큰 쪽
                             : same.lower(cur);                 // 내림차순(negs) → cur 보다 작은 쪽
        int sameSum = sameMate == null ? Integer.MAX_VALUE : Math.abs(cur + sameMate);

        int abs = Math.abs(cur);
        Integer c1 = opp.floor(abs);
        Integer c2 = opp.ceiling(abs);

        Integer oppMate = null;
        int oppSum = Integer.MAX_VALUE;
        if (c1 != null) { oppMate = c1; oppSum = Math.abs(cur + c1); }
        if (c2 != null && Math.abs(cur + c2) < oppSum) {
            oppMate = c2;
            oppSum = Math.abs(cur + c2);
        }

        if (sameSum < oppSum && sameMate != null) {
            pq.offer(new Pair(cur, sameMate, sameSum));
        } else if (oppMate != null) {
            pq.offer(new Pair(cur, oppMate, oppSum));
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        TreeSet<Integer> negs  = new TreeSet<>(Collections.reverseOrder()); // 음수: 내림차순
        TreeSet<Integer> poses = new TreeSet<>();                           // 양수: 오름차순

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (v > 0) poses.add(v); else negs.add(v);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // 기존 로직 유지: 음수 집합 → 양수 집합 순으로 모두 탐색
        for (int n : negs)  process(n, negs,  poses, pq);
        for (int p : poses) process(p, poses, negs,  pq);

        Pair ans = pq.poll();                    // 항상 최소 한 쌍 존재 (N ≥ 2 전제)
        int a = Math.min(ans.n1, ans.n2);
        int b = Math.max(ans.n1, ans.n2);
        System.out.println(a + " " + b);
    }
}
