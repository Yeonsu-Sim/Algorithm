import java.io.*;

public class Main {
    static int x1, y1, p1, q1, w1, h1;
    static int x2, y2, p2, q2, w2, h2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<4; i++) {
            line = br.readLine().split(" ");
            x1 = Integer.parseInt(line[0]);
            y1 = Integer.parseInt(line[1]);
            p1 = Integer.parseInt(line[2]);
            q1 = Integer.parseInt(line[3]);
            w1 = p1-x1;
            h1 = q1-y1;


            x2 = Integer.parseInt(line[4]);
            y2 = Integer.parseInt(line[5]);
            p2 = Integer.parseInt(line[6]);
            q2 = Integer.parseInt(line[7]);
            w2 = p2-x2;
            h2 = q2-y2;

            // 공통부분 없음
            if (p1 < x2 || p2 < x1  || q1 < y2 || q2 < y1) {
                sb.append('d');
            }
            // 점
            else if ((p1 == x2 && q1 == y2) || (p2 == x1 && q2 == y1) ||
                    (x1+w1 == x2 && y1 == y2+h2) || (x1 == x2+w2 && y2 == y1+h1)) {
                sb.append('c');
            }
            // 선분
            else if (x1 == p2 && y1 < q2 && y2 < q1 || x2 == p1 && y2 < q1 && y1 < q2 ||
                    y1 == q2 && x1 < p1 && x2 < q1 || y2 == q1 && x2 < p2 && x1 < q2) {
                sb.append('b');
            }

            // 직사각형
            else {
                sb.append('a');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
;