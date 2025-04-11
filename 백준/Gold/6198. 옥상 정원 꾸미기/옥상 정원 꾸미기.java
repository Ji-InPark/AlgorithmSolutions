

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
        var result = 0L;
        var s = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(br.readLine());

            while (!s.isEmpty() && s.peek() <= num) {
                s.pop();
            }
            s.add(num);

            result += s.size() - 1;
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

