import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int t=0; t<T; t++) {
      String P = br.readLine();
      int N = Integer.parseInt(br.readLine());
      String line = br.readLine();
      line = line.substring(1,line.length()-1);
      String[] tmp = line.split(",");
      ArrayDeque<String> arr = new ArrayDeque<>();
      for (int i=0; i<N; i++) {
        arr.offerLast(tmp[i]);
      }

      boolean reversed = false;
      String result = "";
      for (int i=0; i<P.length(); i++) {
        char p = P.charAt(i);
        if (p == 'R') {
          reversed = !reversed;
        } else if (p == 'D') {
          if (arr.isEmpty()) {
            result = "error";
            break;
          } else {
            if (reversed) {
              arr.pollLast();
            } else {
              arr.pollFirst();
            }
          }
        }
      }

      if (result.equals("")) {
        sb.append('[');
        while (!reversed && !arr.isEmpty()) {
          sb.append(arr.pollFirst());
          if (arr.size() > 0) {
            sb.append(',');
          }
        }
        while (reversed && !arr.isEmpty()) {
          sb.append(arr.pollLast());
          if (arr.size() > 0) {
            sb.append(',');
          }
        }
        sb.append(']').append('\n');
      } else {
        sb.append(result+'\n');
      }
    }

    System.out.print(sb);
  }
}
