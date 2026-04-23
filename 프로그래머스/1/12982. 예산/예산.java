import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        
        int used = 0;
        int idx = 0;
        while (used < budget && idx < d.length) {
            used += d[idx++];
            if (used <= budget) answer++;
        }
        
        return answer;
    }
}