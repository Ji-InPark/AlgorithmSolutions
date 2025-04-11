

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

        var n = Integer.parseInt(br.readLine());
        var q = new LinkedList<int[]>();
        q.add(new int[]{1, 0});
        var visited = new HashSet<Integer>();
        visited.add(1);

        int result = 0;
        Loop:
        while (!q.isEmpty()) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int count = now[0], copy = now[1];

                if (now[0] == n) {
                    break Loop;
                }

                visited.add(count);

                if (!visited.contains(count + copy)) {
                    q.add(new int[]{count + copy, copy});
                }

                if (count != copy) {
                    q.add(new int[]{count, count});
                }

                if (!visited.contains(count - 1)) {
                    q.add(new int[]{count - 1, copy});
                }
            }

            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

