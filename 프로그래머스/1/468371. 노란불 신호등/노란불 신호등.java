class Solution {
    public int solution(int[][] signals) {
        
        int limit = 1;
        for (int[] s : signals) {
            int g = s[0];
            int y = s[1];
            int r = s[2];
            limit *= (g+y+r);
        }
        
        int[] yellows = new int[limit+1];
        
        int cnt = signals.length;
        for (int[] s : signals) {
            int g = s[0];
            int y = s[1];
            int r = s[2];
            
            int len = g+y+r;
            for (int offset=0; offset+g+y<=limit; offset+=len) {
                for (int j=1; j<=y; j++) {
                    if (g+j+offset > limit) break;
                    yellows[g+j+offset]++;
                }   
            }
        }
        
        for (int i=0; i<limit+1; i++) {
            if (yellows[i] == cnt) return i;
        }
        
        return -1;
    }
}