import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zero = 0;
        int correct = 0;
        
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
            
        int l = 0; int w = 0;
        while (l != 6 && w != 6) {
            if (lottos[l] == 0) {
                zero++;
                l++;
            } else if (lottos[l] == win_nums[w]) {
                correct++;
                l++;
                w++;
            } else if (lottos[l] < win_nums[w]) {
                l++;
            } else if (lottos[l] > win_nums[w]) {
                w++;
            }
            
        }
        
        int[] answer = {getRank(correct+zero), getRank(correct)};
        return answer;
    }
    
    // 등수 계산
    public int getRank(int correct) {
        switch (correct) {
            case 6: return 1;
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            case 2: return 5;
            default: return 6;
        }
    }
}