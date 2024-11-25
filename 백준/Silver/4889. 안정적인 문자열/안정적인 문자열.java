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

        for (int i = 1; ; i++) {
            var input = br.readLine();
            if (input.charAt(0) == '-') {
                break;
            }

            var stack = new Stack<Boolean>();
            int result = 0;

            for (var c : input.toCharArray()) {
                var isOpen = c == '{';

                if (stack.isEmpty()) {
                    stack.push(true);
                    if (!isOpen) {
                        result++;
                    }
                } else {
                    if (isOpen) {
                        stack.push(true);
                    } else {
                        stack.pop();
                    }
                }
            }

            result += stack.size() / 2;

            bw.write(i + ". " + result + "\n");
        }
        
        bw.flush();
    }
}
