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

        var input = br.readLine();
        var stack = new Stack<Character>();

        for (var c : input.toCharArray()) {
            switch (c) {
                case '(':
                    stack.add(c);
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        bw.write(stack.pop());
                    }
                    stack.pop();
                    break;
                case '+':
                case '-':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        bw.write(stack.pop());
                    }
                    stack.add(c);
                    break;
                case '*':
                case '/':
                    while (!stack.isEmpty() && stack.peek() != '+' && stack.peek() != '-' && stack.peek() != '(') {
                        bw.write(stack.pop());
                    }
                    stack.add(c);
                    break;
                default:
                    bw.write(c);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            bw.write(stack.pop());
        }

        bw.write("\n");
        bw.flush();
    }
}
