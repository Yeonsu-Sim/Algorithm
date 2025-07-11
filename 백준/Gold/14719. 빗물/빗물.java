import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());

    int[] heights = new int[W];

    st = new StringTokenizer(br.readLine());
    for (int i=0; i<W; i++) {
      heights[i] = Integer.parseInt(st.nextToken());
    }

    int sum =0;
    for (int i=1; i<=H; i++) {
      int start = -1;
      for (int j=0; j<W; j++) {
        if (heights[j] >= i) {
          if (start >= 0) {
            sum += j - start - 1;
          }
          start = j;
        }
      }
    }

    System.out.println(sum);
  }
}
