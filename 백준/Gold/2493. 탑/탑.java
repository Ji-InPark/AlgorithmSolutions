

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

        var n = Integer.parseInt(br.readLine());
        var s = new Stack<int[]>();
        var inputs = br.readLine().split(" ");
        var result = new int[n];

        for (int i = 0; i < n; i++) {
            var now = Integer.parseInt(inputs[i]);

            while (!s.isEmpty() && s.peek()[0] < now) {
                s.pop();
            }

            if (!s.isEmpty()) {
                var prev = s.peek();

                if (prev[0] >= now) {
                    result[i] = prev[1];
                }
            }
			
            s.add(new int[]{now, i + 1});
        }

        for (var num : result) {
            bw.write(num + " ");
        }
        bw.flush();

    }

}
