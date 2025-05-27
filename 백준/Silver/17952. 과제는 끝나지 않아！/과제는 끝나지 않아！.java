

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

        var n = Integer.parseInt(br.readLine());
        var s = new Stack<int[]>();
        var result = 0;

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            if (Integer.parseInt(inputs[0]) == 1) {
                s.add(new int[]{Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])});
            }

            if (!s.isEmpty()) {
                if (--s.peek()[1] == 0) {
                    result += s.pop()[0];
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
