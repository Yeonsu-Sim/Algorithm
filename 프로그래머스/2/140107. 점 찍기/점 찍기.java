import java.util.*;
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for (long i=0; i*k<=d; i++) {
            long a = i*k;
            long b = (int) Math.sqrt((long)d*d-a*a);
            answer += (b/k + 1);
        }
        
        return answer;
    }
}