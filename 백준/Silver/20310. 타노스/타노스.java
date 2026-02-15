import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        
        int zero = 0;
        int one = 0;

        char[] answer = new char[line.length()];

        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            answer[i] = c;
            if (c == '0') zero++;
            else one++;
        }

        zero /= 2;
        one /= 2;

        int idx = line.length()-1;
        while (zero > 0) {
            if (answer[idx] == '0') {
                answer[idx] = ' ';
                zero--;
            }
            idx--;
        }

        idx = 0;
        while (one > 0) {
            if (answer[idx] == '1') {
                answer[idx] = ' ';
                one--;
            }
            idx++;
        }

        StringBuilder sb = new StringBuilder();
        idx = 0;
        while (idx < line.length()) {
            if (answer[idx] != ' ') sb.append(answer[idx]);
            idx++;
        }
        
        System.out.println(sb);
    }
}
