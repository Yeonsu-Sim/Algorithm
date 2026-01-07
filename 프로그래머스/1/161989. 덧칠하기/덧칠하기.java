class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int cur = section[0];
        for (int num : section) {
            if (num < cur) {
                continue;
            }
            cur = num;
            cur += m;
            answer++;
        }
        
        return answer;
    }
}