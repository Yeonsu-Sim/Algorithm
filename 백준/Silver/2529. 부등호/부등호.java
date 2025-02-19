import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String max="", min="";
    static char[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N];

        String line = br.readLine();
        for (int n=0; n<N; n++) {
            arr[n] = line.charAt(n*2);
        }

        searchMax(0, new boolean[10], "");
        searchMin(0, new boolean[10], "");

        System.out.printf("%s\n%s", max, min);
    }

    // 부등호 만족 계산 함수 (부등호 idx의 앞뒤 숫자만 확인)
    static boolean isValid(int idx, String sNums) {
        if (idx < 0) return true;  // 조건 만족 판단 불가능

        int left = Integer.parseInt(sNums.charAt(idx)+"");
        int right = Integer.parseInt(sNums.charAt(idx+1)+"");

        switch (arr[idx]) {
            case '>': {
                return (left > right) ? true : false;
            }
            case '<': {
                return (left < right) ? true : false;
            }
        }
        return false;
    }
    
    // 최댓값 탐색 함수
    static void searchMax(int depth, boolean[] visited, String out) {
        if (!isValid(depth-2, out)) return;  // 부등호 조건 검사

        if (depth == N+1) {  // 재귀 탈출
            max = out;  // 찾았다!
            return;
        }

        for (int i=9; i>=0; i--) {
            if (visited[i]) continue;  // 플래그 검사

            // 재귀 호출
            out += i;
            visited[i] = true;
            searchMax(depth+1, visited, out);
            
            // 만약 만족값을 찾았다면 바로 리턴
            if (!max.equals("")) return;

            // roll-back
            out = out.substring(0, depth);
            visited[i] = false;
        }
    }
    
    // 최솟값 탐색 함수
    static void searchMin(int depth, boolean[] visited, String out) {
        if (!isValid(depth-2, out)) return;  // 부등호 조건 검사
        
        if (depth == N+1) {  // 재귀 탈출
            min = out;  // 찾았다!
            return;
        }
        
        for (int i=0; i<=9; i++) {
            if (visited[i]) continue;  // 플래그 검사

            // 재귀 호출
            out += i;
            visited[i] = true;
            searchMin(depth+1, visited, out);
            
            // 만약 만족값을 찾았다면 바로 리턴
            if (!min.equals("")) return;

            // roll-back
            out = out.substring(0, depth);
            visited[i] = false;
        }
    }

}
