import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        
        int zero = 0;
        int one = 0;

        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '0') zero++;
            else one++;
        }

        StringBuilder sb = new StringBuilder();
        zero /= 2;
        one /= 2;
        while (zero-- > 0) sb.append('0');
        while (one-- > 0) sb.append('1');

        System.out.println(sb);
    }
}
