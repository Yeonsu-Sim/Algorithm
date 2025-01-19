import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = Integer.parseInt(br.readLine());
        int number = Integer.parseInt(br.readLine());
        int sum = 0;

        for (int i = 0; i < number; i++) {
            String s = br.readLine();
            String[] list = s.split(" ");

            int price = Integer.parseInt(list[0]);
            int amount = Integer.parseInt(list[1]);

            sum += price * amount;
        }
        if (total == sum) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}