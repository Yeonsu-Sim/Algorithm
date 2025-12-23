import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int a = N/2;
    int b = a;
    if (a+b < N) b++;

    if (a*b < K) {
      System.out.println(-1);
      return;
    }

    int sum = a*b;
    char[] answer = new char[N];
    for (int i=0; i<a; i++) {
      answer[i] = 'A';
    }
    for (int i=a; i<N; i++) {
      answer[i] = 'B';
    }
    
    int head = a-1;
    int tail = N-1;

    while (sum > K) {
      if (head == tail) {
        tail--;
        for (int i=tail; i>=0; i--) {
          if (answer[i] == 'A') {
            head = i;
            break;
          }
        }
      }

      if (answer[head+1] == 'A') {
        tail = head;
        continue;
      }

      answer[head] = 'B';
      answer[head+1] = 'A';
      head++;
      sum--;
    }

    for (char c : answer) System.out.print(c);
  }
}