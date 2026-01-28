import java.util.*;
class Solution {
    
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int w : weights) {
            // if (map.contains(w)) map.put(w, map.get(w)+1);
            // else map.put(w, 1);
            map.put(w, map.get(w) == null ? 1 : map.get(w)+1);
        }
        
        for (int w : weights) {
            map.put(w, map.get(w)-1);  // 방문처리
            
            // 1:1
            answer += getPair(w,1,1, map);
            
            // 1:2
            answer += getPair(w,1,2, map);
            
            // 2:3
            answer += getPair(w,2,3, map);
            
            // 3:4
            answer += getPair(w,3,4, map);
        }
        
        return answer;
    }
    
    public long getPair(int w, int a, int b, HashMap<Integer,Integer> map) {
        if (w % a != 0) return 0L;
        
        int pair = w/a*b;
        if (map.get(pair) != null) {
            return map.get(pair);
        }
        return 0L;
    }
}