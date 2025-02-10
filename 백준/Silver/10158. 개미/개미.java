import java.io.*;

public class Main {

    static int W, H, X, Y, x, y, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;

        line = br.readLine().split(" ");
        W = Integer.parseInt(line[0]);
        H = Integer.parseInt(line[1]);

        line = br. readLine().split(" ");
        X = Integer.parseInt(line[0]);
        Y = Integer.parseInt(line[1]);

        T = Integer.parseInt(br.readLine());

        int xReflect = (X + T) / W;
        int yReflect = (Y + T) / H;

        x = (X + T) % W;
        y = (Y + T) % H;

         if (xReflect % 2 != 0) {
             x = W - x;
         }
         if (yReflect % 2 != 0) {
             y = H - y;
         }


        System.out.println(x + " " + y);
    }
}
;