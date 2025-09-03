class Solution {
    public int solution(int sticker[]) {
        int size = sticker.length;
        if (size == 1) return sticker[0];

        int[] dpA = new int[size];
        int[] dpB = new int[size];
        
        // 첫번째 선택 & 마지막 선택 못함
        dpA[0] = sticker[0];
        dpA[1] = sticker[0];
        for (int i=2; i<size-1; i++) {
            dpA[i] = Math.max(dpA[i-1], dpA[i-2] + sticker[i]);
        }
        
        // 첫번째 선택 안함
        dpB[0] = 0;
        dpB[1] = sticker[1];
        for (int i=2; i<size; i++) {
            dpB[i] = Math.max(dpB[i-1], dpB[i-2] + sticker[i]);
        }

        return Math.max(dpA[size-2], dpB[size-1]);
    }
    
    // boj 계단 오르기 풀어보기
}