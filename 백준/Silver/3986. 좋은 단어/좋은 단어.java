import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), result = 0;

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split("");
            var stack = new Stack<String>();

            for (var word : inputs) {
                if (!stack.isEmpty() && stack.peek().equals(word)) {
                    stack.pop();
                    continue;
                }

                stack.add(word);
            }

            if (stack.isEmpty()) {
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
