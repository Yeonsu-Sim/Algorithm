import java.util.*;

class Solution {
    public int solution(String[] board) {
        char[] b = new char[9];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                b[i*3+j] = board[i].charAt(j);
            }
        }
        
        char[] t = new char[9];
        Arrays.fill(t, '.');
        return comb(0, b, t) ? 1 : 0;
    }
    
    public boolean comb(int depth, char[] board, char[] target) {
        // if (depth == 9) return false;
        
        if (isSame(board, target)) return true;
        if (isOver(target)) return false;
            
        for (int i=0; i<9; i++) {
            if (target[i] != '.') continue;
            
            target[i] = (depth % 2 == 0) ? 'O' : 'X';
            if (comb(depth+1, board, target)) return true;
            target[i] = '.';
        }
        return false;        
    }
    
    public boolean isSame(char[] board, char[] target) {
        for (int i=0; i<9; i++) {
            if (board[i] != target[i])
                return false;
        }
        return true;
    }
    
    public boolean isOver(char[] target) {
        if (target[0] == target[1] && target[1] == target[2] && target[0] != '.') return true;
        if (target[3] == target[4] && target[4] == target[5] && target[3] != '.') return true;
        if (target[6] == target[7] && target[7] == target[8] && target[6] != '.') return true;
        
        if (target[0] == target[3] && target[3] == target[6] && target[0] != '.') return true;
        if (target[1] == target[4] && target[4] == target[7] && target[1] != '.') return true;
        if (target[2] == target[5] && target[5] == target[8] && target[2] != '.') return true;
        
        if (target[0] == target[4] && target[4] == target[8] && target[0] != '.') return true;
        if (target[2] == target[4] && target[4] == target[6] && target[2] != '.') return true;
        return false;
    }
}