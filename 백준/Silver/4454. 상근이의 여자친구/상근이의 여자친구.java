import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double a = Double.parseDouble(st.nextToken());
                double b = Double.parseDouble(st.nextToken());
                double c = Double.parseDouble(st.nextToken());
                double d = Double.parseDouble(st.nextToken());
                double m = Double.parseDouble(st.nextToken());
                double t = Double.parseDouble(st.nextToken());
        
                double maxV = 0;
                for (double j=0; j<=100000; j++) {
                    double v = j/100;
                    if (t/m >= a*v*v*v + b*v*v + c*v + d) maxV = v;
                    else break;
                }
                sb.append(String.format("%.2f\n", maxV));
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(sb);
    }
}