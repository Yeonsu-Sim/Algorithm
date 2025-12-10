class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i=0; i<schedules.length; i++) {
            boolean isLate = false;
            for (int j=0; j<timelogs[i].length; j++) {
                if (j == (6+7-startday)%7 || j == 7-startday) continue;
                
                if (plus10m(schedules[i]) < timelogs[i][j]) {
                    isLate = true;
                    break;
                }
            }
            if (!isLate) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public int plus10m(int time) {
        int hour = time/100;
        int min = (time%100) + 10;
        if (min >= 60) {
            min -= 60;
            hour++;
        }
        if (hour >= 24) hour-=24;
        return hour*100 + min;
    }
}