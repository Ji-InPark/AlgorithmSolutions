

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int f = Integer.parseInt(inputs[0]), s = Integer.parseInt(inputs[1]), g = Integer.parseInt(inputs[2]),
                u = Integer.parseInt(inputs[3]), d = Integer.parseInt(inputs[4]);

        var dx = new int[]{u, -d};
        var visited = new boolean[f + 1];
        visited[s] = true;
        var q = new LinkedList<Integer>();
        q.add(s);

        var success = false;
        var level = 0;
        Loop:
        while (!q.isEmpty()) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();

                if (now == g) {
                    success = true;
                    break Loop;
                }

                for (int j = 0; j < 2; j++) {
                    var next = now + dx[j];

                    if (0 < next && next <= f && !visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }

            level++;
        }

        if (success) {
            bw.write(level + "\n");
        } else {
            bw.write("use the stairs");
        }
        bw.flush();
    }
}

