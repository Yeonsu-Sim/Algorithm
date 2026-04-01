import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int pair = 0;
        for (int i=people.length-1; i>=0; i--) {
            if (pair <= i) {
                answer++;
            }
            if (pair > i) break;
            if (people[pair]+people[i] <= limit) pair++;
        }
        
        return answer;
    }
}