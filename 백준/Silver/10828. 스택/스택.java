import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        var s = new Stack<>();

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            if (input.contains("push")) {
                String[] commands = input.split(" ");

                s.add(Integer.parseInt(commands[1]));
            } else if (input.contains("pop")) {
                if (s.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(s.pop() + "\n");
                }
            } else if (input.contains("size")) {
                bw.write(s.size() + "\n");
            } else if (input.contains("empty")) {
                bw.write(s.isEmpty() ? "1\n" : "0\n");
            } else {
                if (s.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(s.peek() + "\n");
                }
            }
        }

        bw.flush();
    }
}
