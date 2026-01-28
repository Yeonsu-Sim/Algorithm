class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int ab = brown + yellow;
        
        for (int i=3; i*i<=ab; i++) {
            if (ab % i == 0) {
                int j = ab/i;
                if (2*i+2*j-4 == brown) {
                    answer = new int[]{Math.max(i,j), Math.min(i,j)};                
                }
            }
        }
        
        return answer;
    }
}