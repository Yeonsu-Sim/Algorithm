class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        
        char[] cs = s.toCharArray();
        
        for (int i=0; i<cs.length; i++) {
            char target = cs[i];
            int count = 0;
            int cur = target;
            
            while (count < index) {
                cur++;
                if (cur > 'z') cur = 'a';
                if (!isContains(skip, (char)cur)) {
                    count++;
                }

            }
            
            answer += (char)cur;
        }
        
        return answer;
    }
    
    public boolean isContains(String skip, char c) {
        char[] ss = skip.toCharArray();
        for (int i=0; i<ss.length; i++) {
            if (c == ss[i]) return true;
        }
        return false;
    }
}