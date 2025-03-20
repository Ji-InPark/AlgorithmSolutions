

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);
        var ladders = new HashMap<Integer, Integer>();
        var snakes = new HashMap<Integer, Integer>();

        var visited = new boolean[101];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            ladders.put(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");

            snakes.put(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }

        var q = new LinkedList<Integer>();
        q.add(1);
        visited[1] = true;

        var result = 0;
        Loop:
        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();

                if (now == 100) {
                    break Loop;
                }

                for (int j = 1; j <= 6; j++) {
                    var next = now + j;

                    if (next <= 100 && !visited[next]) {
                        if (ladders.containsKey(next)) {
                            q.add(ladders.get(next));
                            visited[ladders.get(next)] = true;
                        } else if (snakes.containsKey(next)) {
                            q.add(snakes.get(next));
                            visited[snakes.get(next)] = true;
                        } else {
                            q.add(next);
                        }
                        visited[next] = true;
                    }
                }
            }

            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

