import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        int[][] matrix = new int[N][N];
        HashMap<String, Integer> index = new HashMap<>();
        HashMap<String, Integer> sent = new HashMap<>();
        HashMap<String, Integer> received = new HashMap<>();
        HashMap<String, Integer> nextGift = new HashMap<>();
        
        int idx = 0;
        for (String f : friends) {
            index.put(f, idx++);
            sent.put(f, 0);
            received.put(f, 0);
            nextGift.put(f, 0);
        }
        
        for (String g : gifts) {
            StringTokenizer st = new StringTokenizer(g);
            String from = st.nextToken();
            String to = st.nextToken();
            matrix[index.get(from)][index.get(to)]++;
            sent.put(from, sent.get(from)+1);
            received.put(to, received.get(to)+1);
        }
        
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                String from = friends[i];
                String to = friends[j];
                
                if (matrix[i][j] > matrix[j][i]) {
                    nextGift.put(from, nextGift.get(from)+1);
                } else if (matrix[i][j] < matrix[j][i]) {
                    nextGift.put(to, nextGift.get(to)+1);
                } else {
                    int fromStat = sent.get(from) - received.get(from);
                    int toStat = sent.get(to) - received.get(to);
                    if (fromStat > toStat) {
                        nextGift.put(from, nextGift.get(from)+1);
                    } else if (fromStat < toStat) {
                        nextGift.put(to, nextGift.get(to)+1);
                    }
                }
            }
        }
        
        System.out.println(sent);
        System.out.println(received);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : nextGift.values()) {
            pq.offer(n);
        }
        
        return pq.poll();
    }
}