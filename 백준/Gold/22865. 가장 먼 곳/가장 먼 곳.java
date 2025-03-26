

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var starts = new int[3];
        var startInputs = br.readLine().split(" ");
        var results = new int[3][n + 1];
        for (int i = 0; i < 3; i++) {
            starts[i] = Integer.parseInt(startInputs[i]);
        }

        var map = new HashMap<Integer, ArrayList<int[]>>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        var m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int start = Integer.parseInt(inputs[0]), end = Integer.parseInt(inputs[1]),
                    distance = Integer.parseInt(inputs[2]);

            map.get(start).add(new int[]{end, distance});
            map.get(end).add(new int[]{start, distance});
        }

        for (int k = 0; k < 3; k++) {
            var visited = results[k];
            Arrays.fill(visited, Integer.MAX_VALUE);
            visited[starts[k]] = 0;

            var q = new LinkedList<int[]>();
            q.add(new int[]{starts[k], 0});

            while (!q.isEmpty()) {
                var now = q.poll();

                for (var next : map.get(now[0])) {
                    if (visited[next[0]] > next[1] + now[1]) {
                        visited[next[0]] = next[1] + now[1];
                        q.add(new int[]{next[0], next[1] + now[1]});
                    }
                }
            }
        }

        int max = 0, maxIndex = 1;
        for (int i = 1; i <= n; i++) {
            var min = results[0][i];
            for (int j = 0; j < 3; j++) {
                min = Math.min(min, results[j][i]);
            }

            if (max < min) {
                max = min;
                maxIndex = i;
            }
        }

        bw.write(maxIndex + "\n");
        bw.flush();
    }
}

