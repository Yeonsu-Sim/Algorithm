import java.util.*;
import java.io.*;

public class Main {
    public static double fuel(double a, double b, double c, double d, double m, double v) {
        return m*(a * Math.pow(v, 3) + b * Math.pow(v, 2) + c * Math.pow(v, 1) + d);
    }

    public static void main(String[] args) throws IOException {
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

                double start = 1;
                double end = 1000;
                double answer = 0;

                int range=400;
                while (range-->0) {
                    double mid = (start + end) / 2;

                    if (t < fuel(a, b, c, d, m, mid))
                        end = mid;
                    else {
                        answer = mid;
                        start = mid;
                    }
                }

                sb.append(String.format("%.2f\n",(double)(int)(answer*100)/100));
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(sb);
    }
}