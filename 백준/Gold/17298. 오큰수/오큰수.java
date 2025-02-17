import java.io.*;
import java.util.*;

/**
 * ISSUE: 시간 초과
 * CLUE: 본인보다 큰 본인의 오른쪽에 있는 수 중에서 가장 왼쪽에 있는 수 찾기
 *  -> 본인보다 큰 수가 오랫동안 나오지 않으면 어쩔 건가?
 *  -> stack 자료구조를 이용하여 오큰수가 나오지 않은 넘들을 보관하기
 *  -> 본인보다 크던 작던 일단 push
 *  -> 오큰수를 찾은 넘들은 pop
 *  -> node 삽입과 생성 보두 N번씩 진행 -> O(N)
 */
public class Main {

    static int N;
    static int[] answer;
    static ArrayDeque<Node> stack = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        answer = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i=0; i<N; i++)  {
            int num = Integer.parseInt(line[i]);

            while(!stack.isEmpty()) {
                Node peek = stack.peekLast();
                if (peek.value < num) {  // peek 보다 num이 컸을 때
                    answer[peek.idx] = num;
                    stack.pollLast();
                } else {
                    break;
                }
            }
            stack.offer(new Node(i, num));
        }

        while(!stack.isEmpty()) {
            Node node = stack.pollLast();
            answer[node.idx] = -1;
        }

        for (int i=0; i<N; i++) {
            sb.append(answer[i]).append(' ');
        }

        System.out.println(sb);
    }
}

class Node {
    int idx;
    int value;

    Node(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}
