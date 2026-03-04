import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int l=0; l<bridge_length; l++) q.offer(0);
        
        int idx = 0;
        int curWeight = 0;
        while (idx < truck_weights.length) {
            time++;
            
            curWeight -= q.poll();
            int truck = truck_weights[idx];
            if (curWeight + truck > weight) {
                q.offer(0);
                continue;
            }
            
            curWeight += truck;
            q.offer(truck);
            idx++;
        }
        
        return time+bridge_length;
    }
}