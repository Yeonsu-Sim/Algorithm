class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;
        int m = beginning[0].length;
        int answer = Integer.MAX_VALUE;
        
        // 행을 뒤집는 모든 경우 탐색 (비트마스크)
        for (int bit = 0; bit < (1 << n); bit++) {
            int count = 0;
            int[][] temp = new int[n][m];
            
            // beginning 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = beginning[i][j];
                }
            }
            
            // 선택된 행 뒤집기
            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) != 0) {
                    count++;
                    for (int j = 0; j < m; j++) {
                        temp[i][j] ^= 1;
                    }
                }
            }
            
            boolean possible = true;
            
            // 열 검사
            for (int j = 0; j < m; j++) {
                boolean same = true;
                boolean diff = true;
                
                for (int i = 0; i < n; i++) {
                    if (temp[i][j] != target[i][j]) {
                        same = false;
                    }
                    if (temp[i][j] == target[i][j]) {
                        diff = false;
                    }
                }
                
                if (same) continue;
                else if (diff) count++;
                else {
                    possible = false;
                    break;
                }
            }
            
            if (possible) {
                answer = Math.min(answer, count);
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}