import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer> blackList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		for (int i=0; i<B; i++) blackList.add(Integer.parseInt(st.nextToken()));
		
		List<List<Integer>> invites = new ArrayList<>();
		for (int i=0; i<=N; i++) invites.add(new ArrayList<>());
		List<List<Integer>> parties = new ArrayList<>();
		for (int i=0; i<M; i++) parties.add(new ArrayList<>());

		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j=0; j<size; j++) {
				int participant = Integer.parseInt(st.nextToken());
				parties.get(i).add(participant);
				invites.get(participant).add(i);
			}
		}

		boolean[] canLie = new boolean[M];
		Arrays.fill(canLie, true);
		Queue<Integer> q = new ArrayDeque<>();  // 파티 담기
		for (int black : blackList) {
			for (int p : invites.get(black)) {
				if (!canLie[p]) continue;
				canLie[p] = false;
				q.offer(p);
			}
		}

		while (!q.isEmpty()) {
			int curparties = q.poll();
			for (int participant : parties.get(curparties)) {
				for (int nextparty : invites.get(participant)) {
					if (!canLie[nextparty]) continue;
					canLie[nextparty] = false;
					q.offer(nextparty);
				}
			}
		}

		int answer = 0;
		for (boolean c : canLie) {
			if (c) answer++;
		}

		System.out.println(answer);
	}
}