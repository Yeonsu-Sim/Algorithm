class Solution {
    public int[] solution(String[] wallpaper) {
        int minX = 50; int minY = 50;
        int maxX = 0; int maxY = 0;
        
        for (int i=0; i<wallpaper.length; i++) {
            for (int j=0; j<wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    minX = Math.min(minX, j);
                    minY = Math.min(minY, i);
                    maxX = Math.max(maxX, j+1);
                    maxY = Math.max(maxY, i+1);
                }
            }
        }
        
        int[] answer = {minY, minX, maxY, maxX};
        return answer;
    }
}