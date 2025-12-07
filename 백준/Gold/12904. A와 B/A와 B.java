import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String S = br.readLine();
    String T = br.readLine();

    while (S.length() < T.length()) {
      switch(T.charAt(T.length()-1)) {
        case 'A' -> {
          T = T.substring(0,T.length()-1);
        }
        case 'B' -> {
          T = reverse(T.substring(0,T.length()-1));
        }
      }
    }

    System.out.println(S.equals(T) ? 1 : 0);
  }

  public static String reverse(String str) {
    String rev = "";
    for (int i=str.length()-1; i>= 0; i--) {
      rev += str.charAt(i);
    }
    return rev;
  }
}