

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int start = Integer.parseInt(inputs[0]), end = Integer.parseInt(inputs[1]), level = 0;

        var q = new LinkedList<Integer>();
        q.add(start);
        var visited = new HashSet<Integer>();

        Loop:
        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();

                if (now == end) {
                    break Loop;
                }

                if (!visited.contains(now + 1) && now + 1 <= 1000000) {
                    q.add(now + 1);
                    visited.add(now + 1);
                }

                if (!visited.contains(now * 2) && now * 2 <= 1000000) {
                    q.add(now * 2);
                    visited.add(now * 2);
                }
            }

            level++;
        }

        bw.write(level + "\n");
        bw.flush();
    }
}
