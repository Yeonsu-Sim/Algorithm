import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int time = Integer.parseInt(br.readLine());

        for (int i = 0; i < time; i++) {
            String line = br.readLine();
            String[] numbers = line.split(" ");
            int fst = Integer.parseInt(numbers[0]);
            int snd = Integer.parseInt(numbers[1]);
            bw.write(fst + snd +"\n");
        }
        bw.flush();
    }
}