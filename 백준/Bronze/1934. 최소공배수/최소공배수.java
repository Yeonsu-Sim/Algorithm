import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 시스템으로부터 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받을 개수
        int T = Integer.parseInt(br.readLine());
        
        // 최소공배수 저장할 배열
        int[] result = new int[T];
        
        for(int i=0;i<T;i++){  // 입력 받을 개수만큼 반복
            String[] sa = br.readLine().split(" ");  // 주어진 두 수를 저장할 배열
            int A = Integer.parseInt(sa[0]);  // 첫번째 수를 int형으로 변환
            int B = Integer.parseInt(sa[1]);  // 두번째 수를 int형으로 변환
            
            
            int min = Math.min(A,B);  // 둘중 더 작은 것을 선택
            int num=1;  // 최대공약수 초기화


            for(int j=1; j<=min; j++){  // 더 작은 수보다 작거나 같은 최대공약수 구하기
                if((A%j) == 0 && (B%j) ==0){  // 두 수를 모두 나누어 떨어지게 하는 수라면
                    num = j;  // 최대공약수 갱신
                }
            }

            // 최대공배수 = 최대공약수 * (두 수를 각각 최대공약수로 나눈 나머지의 곱)
            result[i] = A*B/num;

        }
        
        // 결과출력하기
        for(int i=0;i<T;i++){
            System.out.println(result[i]);
        }
    }
}