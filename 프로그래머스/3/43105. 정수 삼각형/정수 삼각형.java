class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        
        for (int i=N-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                triangle[i-1][j] += Math.max(triangle[i][j], triangle[i][j+1]);
            }
        }
        return triangle[0][0];
    }
}